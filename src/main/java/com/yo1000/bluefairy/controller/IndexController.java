package com.yo1000.bluefairy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yoichi.kikuchi on 15/02/19.
 */
@Controller
@RequestMapping("")
public class IndexController {
    @RequestMapping("")
    public String index() {
        return "redirect:/container/";
    }
}