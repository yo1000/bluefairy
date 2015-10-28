package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.entity.docker.ImageInspect;
import com.yo1000.bluefairy.model.service.ImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/02/19.
 */
@Controller
@RequestMapping("image")
public class ImageController {
    @Resource
    private ImageService imageService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVEL')")
    public String index(Model model) {
        model.addAttribute("title", "Images");
        model.addAttribute("images", this.getImageService().getImages());

        return "images";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVEL')")
    public String all(Model model) {
        model.addAttribute("title", "All images");
        model.addAttribute("images", this.getImageService().getImagesAll());

        return "images";
    }

    @RequestMapping(value = "{id:(?!^all$).+}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVEL')")
    public String id(@PathVariable String id, Model model) {
        ImageInspect image = this.getImageService().getImage(id);

        model.addAttribute("title", "Image " + image.getIdToShort());
        model.addAttribute("image", image);

        return "image";
    }

    @RequestMapping(value = "pull/{image}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String pull(@PathVariable String image) {
        this.getImageService().createImage(image);

        return "redirect:/image/";
    }

    protected ImageService getImageService() {
        return imageService;
    }
}
