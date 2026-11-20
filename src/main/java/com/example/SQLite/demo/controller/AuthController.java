package com.example.SQLite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.SQLite.demo.model.DeliveryProfile;
import com.example.SQLite.demo.model.RestaurantProfile;
import com.example.SQLite.demo.model.User;
import com.example.SQLite.demo.repository.DeliveryProfileRepository;
import com.example.SQLite.demo.repository.RestaurantProfileRepository;
import com.example.SQLite.demo.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // ================= SIGNUP PAGE =================
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    // ================= SIGNUP SUBMIT =================
    @PostMapping("/signup")
    public String signupSubmit(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            Model model
    ) {
        // ❌ duplicate email check
        if (userRepository.findByEmail(email) != null) {
            model.addAttribute("error", "Email already exists");
            return "signup";
        }

        // create base user
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);   // password encode later
        user.setRole(role);
        user.setStatus("ACTIVE");

        userRepository.save(user);   // 🔑 ID GENERATED HERE

        // 🔀 ROLE WISE REDIRECT
        if (role.equals("USER")) {
            return "redirect:/signup/user/" + user.getId();
        }
        else if (role.equals("RESTAURANT")) {
            return "redirect:/signup/restaurant/" + user.getId();
        }
        else if (role.equals("DELIVERY")) {
            return "redirect:/signup/delivery/" + user.getId();
        }
        else {
            return "redirect:/signup";
        }
    }

    // ================= SIGNUP SUCCESS PAGE =================
    @GetMapping("/signup-success")
    public String signupSuccess() {
        return "signup-success";
    }




    // USER signup page
    @GetMapping("/signup/user/{userId}")
    public String userSignupPage(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "user-signup";
    }

    // RESTAURANT signup page
    @GetMapping("/signup/restaurant/{userId}")
    public String restaurantSignupPage(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "restaurant-signup";
    }

    // DELIVERY signup page
    @GetMapping("/signup/delivery/{userId}")
    public String deliverySignupPage(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "delivery-signup";
    }







    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(
            @RequestParam String email,
            @RequestParam String password,
            jakarta.servlet.http.HttpSession session,
            Model model
    ) {
        User user = userRepository.findByEmail(email);

        // invalid login
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        // blocked user
        if ("BLOCKED".equals(user.getStatus())) {
            model.addAttribute("error", "Account blocked");
            return "login";
        }

        // session store
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());

        // 🔀 ROLE BASED REDIRECT
        if (user.getRole().equals("USER")) {
            return "redirect:/user/dashboard";
        }
        else if (user.getRole().equals("RESTAURANT")) {
            return "redirect:/restaurant/dashboard";
        }
        else if (user.getRole().equals("DELIVERY")) {
            return "redirect:/delivery/dashboard";
        }
        else if (user.getRole().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }

        return "redirect:/login";
    }




    @PostMapping("/signup/user")
    public String saveUserProfile(
            @RequestParam Long userId,
            @RequestParam String name,
            @RequestParam String nid
    ) {
        // এখন শুধু test এর জন্য save না করলেও চলবে
        // পরের step এ UserProfile entity + repository বসাবো

        System.out.println("User profile saved for userId = " + userId);

        return "redirect:/signup-success";
    }



   @Autowired
    private DeliveryProfileRepository deliveryProfileRepository;

    @PostMapping("/signup/delivery")
    public String saveDeliveryProfile(
            @RequestParam Long userId,
            @RequestParam String name,
            @RequestParam String contact,
            @RequestParam double commissionRate
    ) {
        DeliveryProfile profile = new DeliveryProfile();
        profile.setUserId(userId);
        profile.setName(name);
        profile.setContact(contact);
        profile.setCommissionRate(commissionRate);
        profile.setAvailability("AVAILABLE"); // 🔥 VERY IMPORTANT

        deliveryProfileRepository.save(profile); // 🔥 THIS WAS MISSING

        return "redirect:/signup-success";
    }






    @Autowired
    private RestaurantProfileRepository restaurantProfileRepository;

    @PostMapping("/signup/restaurant")
    public String saveRestaurantProfile(
            @RequestParam Long userId,
            @RequestParam String restaurantName,
            @RequestParam String ownerName,
            @RequestParam String tradeId,
            @RequestParam String address
    ) {
        RestaurantProfile profile = new RestaurantProfile();
        profile.setUserId(userId);
        profile.setRestaurantName(restaurantName);
        profile.setOwnerName(ownerName);
        profile.setTradeId(tradeId);
        profile.setAddress(address);
        profile.setStatus("ACTIVE");

        restaurantProfileRepository.save(profile); // 🔥 MUST

        return "redirect:/signup-success";
    }
}
