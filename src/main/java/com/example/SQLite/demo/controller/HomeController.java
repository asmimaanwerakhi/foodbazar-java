package com.example.SQLite.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.SQLite.demo.model.FoodPost;
import com.example.SQLite.demo.repository.FoodPostRepository;

@Controller
public class HomeController {

    @Autowired
    private FoodPostRepository foodPostRepository;

    // HOME PAGE
    @GetMapping("/")
    public String home(Model model) {

        List<FoodPost> foods = foodPostRepository.findAll();

        model.addAttribute("foods", foods);

        return "home";   // home.html
    }
}
