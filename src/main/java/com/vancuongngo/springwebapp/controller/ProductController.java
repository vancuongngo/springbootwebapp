package com.vancuongngo.springwebapp.controller;

import com.vancuongngo.springwebapp.model.Product;
import com.vancuongngo.springwebapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping()
    public String saveProduct(@Valid Product product, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productService.saveProduct(product);
        redirect.addFlashAttribute("success", "Saved product successfully");
        return "redirect:/product/" + product.getId();
    }

    @GetMapping(value = "/{id}")
    public String showProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-show";
    }

    @GetMapping(value = "/all")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }

    @GetMapping(value = "/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-form";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
