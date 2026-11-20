package com.example.SQLite.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SQLite.demo.model.DeliveryProfile;
import com.example.SQLite.demo.model.DeliveryRequest;
import com.example.SQLite.demo.model.DeliveryLog;

import com.example.SQLite.demo.repository.DeliveryProfileRepository;
import com.example.SQLite.demo.repository.DeliveryRequestRepository;
// import com.example.SQLite.demo.repository.OrderRepository;
import com.example.SQLite.demo.repository.DeliveryLogRepository;

// import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryProfileRepository deliveryProfileRepo;

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepo;

    @Autowired
    private DeliveryLogRepository deliveryLogRepo;

    // @Autowired
    // private OrderRepository orderRepo;

    @GetMapping("/dashboard")
public String dashboard(HttpSession session, Model model) {

    Long deliveryUserId = (Long) session.getAttribute("userId");

    // 🔐 Safety: login check
    if (deliveryUserId == null) {
        return "redirect:/login";
    }

    // 1️⃣ Fetch delivery profile
    DeliveryProfile profile =
            deliveryProfileRepo.findByUserId(deliveryUserId);

    // ❗ NULL CHECK (THIS IS WHAT YOU ASKED)
    if (profile == null) {
        model.addAttribute("error",
                "Delivery profile not found. Please complete signup.");
        return "delivery-dashboard";
    }

    // 2️⃣ Pending requests (only if AVAILABLE)
    List<DeliveryRequest> pendingRequests = List.of();
    if ("AVAILABLE".equals(profile.getAvailability())) {
        pendingRequests =
                deliveryRequestRepo.findByDeliveryUserIdAndStatus(
                        deliveryUserId, "PENDING");
    }

    // 3️⃣ Active delivery (when BUSY)
    DeliveryLog activeDelivery =
            deliveryLogRepo.findByDeliveryUserIdAndStatus(
                    deliveryUserId, "ASSIGNED")
                    .stream().findFirst().orElse(null);

    // 4️⃣ Total income
    double totalIncome = deliveryLogRepo
            .findByDeliveryUserId(deliveryUserId)
            .stream()
            .mapToDouble(DeliveryLog::getAmount)
            .sum();

    // 5️⃣ Model attributes
    model.addAttribute("profile", profile);
    model.addAttribute("requests", pendingRequests);
    model.addAttribute("activeDelivery", activeDelivery);
    model.addAttribute("income", totalIncome);

    return "delivery-dashboard";
}

    @PostMapping("/accept")
    public String acceptDelivery(@RequestParam Long requestId,
                                HttpSession session) {

        Long deliveryUserId = (Long) session.getAttribute("userId");

        DeliveryRequest req =
            deliveryRequestRepo.findById(requestId).orElse(null);

        if (req != null) {

            // 1️⃣ Accept request
            req.setStatus("ACCEPTED");
            deliveryRequestRepo.save(req);

            // 2️⃣ Add delivery log
            DeliveryLog log = new DeliveryLog();
            log.setDeliveryUserId(deliveryUserId);
            log.setOrderId(req.getOrderId());
            log.setAmount(req.getDeliveryCharge());
            log.setStatus("ASSIGNED");
            deliveryLogRepo.save(log);

            // 3️⃣ Set delivery boy BUSY
            DeliveryProfile profile =
                deliveryProfileRepo.findByUserId(deliveryUserId);
            profile.setAvailability("BUSY");
            deliveryProfileRepo.save(profile);
        }

        return "redirect:/delivery/dashboard";
    }


    @PostMapping("/complete")
    public String completeDelivery(@RequestParam Long logId,
                                HttpSession session) {

        Long deliveryUserId = (Long) session.getAttribute("userId");

        DeliveryLog log =
            deliveryLogRepo.findById(logId).orElse(null);

        if (log != null) {
            log.setStatus("COMPLETED");
            deliveryLogRepo.save(log);

            // FREE again
            DeliveryProfile profile =
                deliveryProfileRepo.findByUserId(deliveryUserId);
            profile.setAvailability("AVAILABLE");
            deliveryProfileRepo.save(profile);
        }

        return "redirect:/delivery/dashboard";
    }

}

