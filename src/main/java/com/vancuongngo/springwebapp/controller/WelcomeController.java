package com.vancuongngo.springwebapp.controller;

import com.vancuongngo.springwebapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
