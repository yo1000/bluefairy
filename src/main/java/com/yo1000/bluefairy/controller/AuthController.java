package com.yo1000.bluefairy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yoichi.kikuchi on 15/03/31.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @RequestMapping("/login")
    public String index(Model model) {
        model.addAttribute("title", "Login");

        return "login";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        return "redirect:/auth/login/";
    }
}
