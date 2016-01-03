package com.yo1000.bluefairy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yo1000.bluefairy.model.service.InfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Controller
@RequestMapping("info")
@PreAuthorize("hasAnyRole('ADMIN', 'DEVEL', 'USER')")
public class InfoController {
    @Resource
    private InfoService infoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) throws JsonProcessingException {
        model.addAttribute("title", "Info");
        model.addAttribute("info", this.getInfoService().getInfo());
        model.addAttribute("title", "Info");

        return "info";
    }

    protected InfoService getInfoService() {
        return infoService;
    }
}
