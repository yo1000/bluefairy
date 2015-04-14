package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.service.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Controller
@RequestMapping("version")
public class VersionController {
    @Resource
    private VersionService versionService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("version", this.getVersionService().getVersion());
        model.addAttribute("title", "Version");

        return "version";
    }

    protected VersionService getVersionService() {
        return versionService;
    }
}