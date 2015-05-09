package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.entity.docker.ImageSearch;
import com.yo1000.bluefairy.model.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Controller
@RequestMapping("search")
public class SearchController {
    @Resource
    private ImageService imageService;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Image search");
        model.addAttribute("search", new ImageSearch[] {});

        return "search";
    }

    @RequestMapping("{keyword}")
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