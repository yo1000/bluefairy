package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Controller
@RequestMapping("info")
public class InfoController {
    @Resource
    private InfoService infoService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Info");
        model.addAttribute("info", this.getInfoService().getInfo());

        return "info";
    }

    protected InfoService getInfoService() {
        return infoService;
    }
}