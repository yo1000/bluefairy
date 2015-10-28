package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.service.VersionService;
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
@RequestMapping("version")
@PreAuthorize("hasAnyRole('ADMIN', 'DEVEL', 'USER')")
public class VersionController {
    @Resource
    private VersionService versionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Version");
        model.addAttribute("version", this.getVersionService().getVersion());
        model.addAttribute("title", "Version");

        return "version";
    }

    protected VersionService getVersionService() {
        return versionService;
    }
}
