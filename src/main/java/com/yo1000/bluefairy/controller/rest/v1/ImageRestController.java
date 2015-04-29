package com.yo1000.bluefairy.controller.rest.v1;

import com.yo1000.bluefairy.model.entity.docker.Image;
import com.yo1000.bluefairy.model.entity.docker.ImageInspect;
import com.yo1000.bluefairy.model.entity.docker.ImageCreated;
import com.yo1000.bluefairy.model.service.ImageService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/04/17.
 */
@RestController
@RequestMapping("api/v1/image")
public class ImageRestController {
    @Resource
    private ImageService imageService;

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json")
    public Image[] getItems() {
        return this.getImageService().getImages();
    }

    @RequestMapping(value = "all", method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json")
    public Image[] getItemsAll() {
        return this.getImageService().getImagesAll();
    }

    @RequestMapping(value = "{id:(?!all)[a-zA-Z0-9_-]+}", method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json")
    public ImageInspect getItem(@PathVariable String id, Model model) {
        return this.getImageService().getImage(id);
    }

    @RequestMapping(value = "pull/{image}", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json")
    public ImageCreated postPull(@PathVariable String image) {
        return this.getImageService().createImage(image);
    }

    protected ImageService getImageService() {
        return imageService;
    }
}
