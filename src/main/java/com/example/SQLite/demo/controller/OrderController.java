package com.example.SQLite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.SQLite.demo.model.FoodPost;
import com.example.SQLite.demo.model.Order;
import com.example.SQLite.demo.repository.FoodPostRepository;
import com.example.SQLite.demo.repository.OrderRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private FoodPostRepository foodRepo;

    @Autowired
    private OrderRepository orderRepo;

    // 🟢 STEP 1: Order Page
    @GetMapping("/new")
    public String orderPage(
            @RequestParam Long foodId,
            HttpSession session,
            Model model
    ) {

        // login required
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        FoodPost food = foodRepo.findById(foodId).orElse(null);

        if (food == null) {
            return "redirect:/";
        }

        model.addAttribute("food", food);

        return "order-form";   // order-form.html
    }

    // 🟢 STEP 2: Save Order
    @PostMapping("/save")
    public String placeOrder(
            @RequestParam Long foodId,
            @RequestParam int quantity,
            @RequestParam String address,
            HttpSession session
    ) {

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        FoodPost food = foodRepo.findById(foodId).orElse(null);

        if (food == null) {
            return "redirect:/";
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantUserId(food.getRestaurantUserId());
        order.setFoodId(foodId);
        order.setQuantity(quantity);
        order.setTotalPrice(food.getPrice() * quantity);
        order.setDeliveryAddress(address);
        order.setStatus("PLACED");

        orderRepo.save(order);

        return "redirect:/user/orders";
    }
}
