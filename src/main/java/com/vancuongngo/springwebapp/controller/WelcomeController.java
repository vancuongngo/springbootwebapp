package com.vancuongngo.springwebapp.controller;

import com.vancuongngo.springwebapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"", "/index"})
    public String index() {
        productService.saveProducts();
        return "index";
    }
}
