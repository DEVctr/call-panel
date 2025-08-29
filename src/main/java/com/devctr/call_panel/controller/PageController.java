package com.devctr.call_panel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index"; // procura templates/index.html
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }
}