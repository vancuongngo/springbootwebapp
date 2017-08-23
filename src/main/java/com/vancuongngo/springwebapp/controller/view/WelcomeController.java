package com.vancuongngo.springwebapp.controller.view;

import com.vancuongngo.springwebapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "expired", required = false) String expired,
                              HttpServletRequest request,
                              HttpSession session, HttpServletResponse response) {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationException exception = ((AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));

        if (exception instanceof SessionAuthenticationException) {
            session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, null);
            SessionAuthenticationException ex = (SessionAuthenticationException) exception;
            if (ex.getStackTrace() != null) {
                model.addObject("sessionLimit", "The number of sessions concurrency has been reached limitation");
            }
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }

        if (expired != null) {
            model.addObject("expired", "Your session has expired.<br/>Please log in again.");
        }

        model.setViewName("login");

        return model;
    }
}
