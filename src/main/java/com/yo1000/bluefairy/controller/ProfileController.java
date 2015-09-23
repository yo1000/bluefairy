package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yoichi.kikuchi on 15/02/19.
 */
@Controller
@RequestMapping("profile")
public class ProfileController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String update(Authentication authentication, Model model) {
        User user = this.getUserService().getUserByUsername(authentication.getName());

        model.addAttribute("title", "Profile");
        model.addAttribute("user", user);

        return "user/profile";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String update(@RequestParam String id,
                         @RequestParam String username, @RequestParam String password,
                         @RequestParam String role, @RequestParam String fullname,
                         HttpServletRequest request) throws ServletException {
        this.getUserService().updateUser(id, username, password, role, fullname);

        return "redirect:/auth/logout";
    }

    protected UserService getUserService() {
        return userService;
    }
}
