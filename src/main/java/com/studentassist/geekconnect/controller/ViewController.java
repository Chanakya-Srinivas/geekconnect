package com.studentassist.geekconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "index.html";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard.html";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile.html";
    }

    // Add mappings for other frontend files as needed
}
