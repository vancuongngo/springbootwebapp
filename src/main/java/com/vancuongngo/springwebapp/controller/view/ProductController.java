package com.vancuongngo.springwebapp.controller.view;

import com.vancuongngo.springwebapp.repository.model.Product;
import com.vancuongngo.springwebapp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/product")
@Slf4j
public class ProductController {

    private ProductService productService;

    private Authentication authentication;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/new")
    public String newProduct(Model model, Principal principal) {
        log.debug("User {} is creating new product", principal.getName());
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
    public String showProduct(@PathVariable int id, Model model, Authentication authenticationParam) {
        log.debug("User {} is viewing product has id = {}", ((User) authenticationParam.getPrincipal()).getUsername(), id);
        model.addAttribute("product", productService.getProductById(id));
        return "product-show";
    }

    @GetMapping(value = "/all")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }

    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-form";
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable int id) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("User {} is doing delete product function with id = {}", ((User) authentication.getPrincipal()).getUsername(), id);
        productService.deleteProduct(id);
        return "redirect:/product/all";
    }
}
