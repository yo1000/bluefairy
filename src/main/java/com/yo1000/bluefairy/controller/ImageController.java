package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.entity.docker.ImageInspect;
import com.yo1000.bluefairy.model.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/02/19.
 */
@Controller
@RequestMapping("image")
public class ImageController {
    @Resource
    private ImageService imageService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Images");
        model.addAttribute("images", this.getImageService().getImages());

        return "images";
    }

    @RequestMapping("all")
    public String all(Model model) {
        model.addAttribute("title", "All images");
        model.addAttribute("images", this.getImageService().getImagesAll());

        return "images";
    }

    @RequestMapping("{id:(?!^all$).+}")
    public String id(@PathVariable String id, Model model) {
        ImageInspect image = this.getImageService().getImage(id);

        model.addAttribute("title", "Image " + image.getIdToShort());
        model.addAttribute("image", image);

        return "image";
    }

    @RequestMapping("pull/{image}")
    public String pull(@PathVariable String image) {
        this.getImageService().createImage(image);

        return "redirect:/image/";
    }

    protected ImageService getImageService() {
        return imageService;
    }
}