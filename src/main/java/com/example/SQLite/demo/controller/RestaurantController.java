package com.example.SQLite.demo.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.SQLite.demo.model.*;
import com.example.SQLite.demo.repository.*;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    // ===================== REPOSITORIES =====================

    @Autowired
    private RestaurantProfileRepository restaurantProfileRepo;

    @Autowired
    private FoodPostRepository foodPostRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepo;

    // ===================== DASHBOARD =====================

    // @GetMapping("/dashboard")
    // public String dashboard(HttpSession session, Model model) {

    //     Long userId = (Long) session.getAttribute("userId");
    //     String role = (String) session.getAttribute("role");

    //     if (userId == null || !"RESTAURANT".equals(role)) {
    //         return "redirect:/login";
    //     }

    //     RestaurantProfile profile =
    //             restaurantProfileRepo.findByUserId(userId);

    //     if (profile == null) {
    //         model.addAttribute("error", "Complete restaurant signup first");
    //         return "restaurant-dashboard";
    //     }

    //     model.addAttribute("profile", profile);
    //     return "restaurant-dashboard";
    // }
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null || !"RESTAURANT".equals(role)) {
            return "redirect:/login";
        }

        RestaurantProfile profile =
                restaurantProfileRepo.findByUserId(userId);

        // 🔥 INCOMING ORDERS
        List<Order> incomingOrders =
                orderRepo.findByRestaurantUserIdAndStatus(
                        userId, "PLACED"
                );

        model.addAttribute("profile", profile);
        model.addAttribute("orders", incomingOrders);

        return "restaurant-dashboard";
    }



    // ===================== ADD FOOD PAGE =====================

    @GetMapping("/food/add")
    public String addFoodPage(HttpSession session) {

        if (!"RESTAURANT".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        return "add-food";
    }

    // ===================== SAVE FOOD =====================

    @PostMapping("/food/add")
    public String saveFood(
            @RequestParam String foodName,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            HttpSession session
    ) throws IOException {

        Long restaurantUserId = (Long) session.getAttribute("userId");
        if (restaurantUserId == null) return "redirect:/login";

        // 🔥 IMAGE UPLOAD
        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        RestaurantProfile profile =
                restaurantProfileRepo.findByUserId(restaurantUserId);

        String restaurantName =
                profile.getRestaurantName().replaceAll("\\s+", "_");

        String time =
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String imageName =
                restaurantName + "_" + time + "_" + image.getOriginalFilename();

        image.transferTo(new File(uploadDir + imageName));

        // SAVE DB
        FoodPost post = new FoodPost();
        post.setRestaurantUserId(restaurantUserId);
        post.setFoodName(foodName);
        post.setPrice(price);
        post.setDescription(description);
        post.setImageName(imageName);

        foodPostRepo.save(post);

        return "redirect:/restaurant/foods";
    }

    // ===================== MY FOODS =====================

    @GetMapping("/foods")
    public String myFoods(HttpSession session, Model model) {

        Long restaurantUserId = (Long) session.getAttribute("userId");
        if (restaurantUserId == null) return "redirect:/login";

        model.addAttribute(
                "foods",
                foodPostRepo.findByRestaurantUserId(restaurantUserId)
        );

        return "restaurant-foods";
    }

    // ===================== VIEW ORDERS =====================

    @GetMapping("/orders")
    public String restaurantOrders(HttpSession session, Model model) {

        Long restaurantUserId = (Long) session.getAttribute("userId");
        if (restaurantUserId == null) return "redirect:/login";

        List<Order> orders =
                orderRepo.findByRestaurantUserId(restaurantUserId);

        model.addAttribute("orders", orders);
        return "restaurant-orders";
    }

    // ===================== SEND DELIVERY =====================

    @PostMapping("/send-delivery")
    public String sendDelivery(@RequestParam Long orderId) {

        Order order = orderRepo.findById(orderId).orElse(null);
        if (order == null) return "redirect:/restaurant/orders";

        DeliveryRequest req = new DeliveryRequest();
        req.setOrderId(order.getId());
        req.setRestaurantUserId(order.getRestaurantUserId());
        req.setDeliveryCharge(60);
        req.setStatus("PENDING");

        deliveryRequestRepo.save(req);

        order.setStatus("DELIVERING");
        orderRepo.save(order);

        return "redirect:/restaurant/orders";
    }





}
