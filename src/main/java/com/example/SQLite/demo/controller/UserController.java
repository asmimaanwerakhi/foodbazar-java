package com.example.SQLite.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SQLite.demo.model.Order;
import com.example.SQLite.demo.model.UserProfile;
import com.example.SQLite.demo.repository.OrderRepository;
import com.example.SQLite.demo.repository.UserProfileRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserProfileRepository userProfileRepo;

    @Autowired
    private OrderRepository orderRepo;

    // 🟢 USER DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("userId");

        // 🔐 login check
        if (userId == null) {
            return "redirect:/login";
        }

        // profile
        UserProfile profile = userProfileRepo.findByUserId(userId);

        // safety check
        if (profile == null) {
            model.addAttribute("error", "Profile not found");
            return "user-dashboard";
        }

        // orders
        List<Order> orders = orderRepo.findByUserId(userId);

        model.addAttribute("profile", profile);
        model.addAttribute("orders", orders);
        model.addAttribute("totalOrders", orders.size());

        return "user-dashboard";
    }

    // 🟢 USER ORDERS PAGE
    @GetMapping("/orders")
    public String myOrders(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        List<Order> orders = orderRepo.findByUserId(userId);
        model.addAttribute("orders", orders);

        return "user-orders";
    }

    // 🟢 LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
