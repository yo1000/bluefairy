package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yoichi.kikuchi on 15/03/31.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String index(Model model) {
        if (!this.getUserService().existsUser()) {
            model.addAttribute("title", "Admin user registration");
            model.addAttribute("first", true);

            return "user/register";
        }

        model.addAttribute("title", "Login");

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String fullname) {
        if (this.getUserService().existsUser()) {
            throw new IllegalStateException("Already exists users.");
        }

        this.getUserService().registerUser(username, password, "ADMIN", fullname);

        return "redirect:/auth/login/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/auth/login/";
    }

    public UserService getUserService() {
        return userService;
    }
}
