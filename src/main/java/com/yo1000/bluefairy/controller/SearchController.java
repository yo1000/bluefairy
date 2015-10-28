package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.entity.docker.ImageSearch;
import com.yo1000.bluefairy.model.service.ImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Controller
@RequestMapping("search")
@PreAuthorize("hasAnyRole('ADMIN', 'DEVEL')")
public class SearchController {
    @Resource
    private ImageService imageService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Image search");
        model.addAttribute("search", new ImageSearch[] {});

        return "search";
    }

    @RequestMapping(value = "{keyword}", method = RequestMethod.GET)
    public String keyword(@PathVariable String keyword, Model model) {
        model.addAttribute("title", "Image search");
        model.addAttribute("keyword", keyword);
        model.addAttribute("search", this.getImageService().getImageSearch(keyword));

        return "search";
    }

    public ImageService getImageService() {
        return imageService;
    }
}
