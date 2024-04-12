package com.studentassist.geekconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "index.html"; // This will serve the dashboard.html file from resources/static directory
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard.html"; // This will serve the dashboard.html file from resources/static directory
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile.html"; // This will serve the profile.html file from resources/static directory
    }

    // Add mappings for other frontend files as needed
}
