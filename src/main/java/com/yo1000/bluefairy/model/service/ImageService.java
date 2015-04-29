package com.yo1000.bluefairy.model.service;

import com.yo1000.bluefairy.model.entity.docker.Image;
import com.yo1000.bluefairy.model.entity.docker.ImageInspect;
import com.yo1000.bluefairy.model.entity.docker.ImageCreated;
import com.yo1000.bluefairy.model.entity.docker.ImageSearch;
import com.yo1000.bluefairy.model.repository.ImageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Service
public class ImageService {
    @Resource
    private ImageRepository imageRepository;

    public Image[] getImages() {
        return this.getImageRepository().getJson();
    }

    public Image[] getImagesAll() {
        return this.getImageRepository().getJson(true);
    }

    public ImageInspect getImage(String id) {
        return this.getImageRepository().getInspect(id);
    }

    public ImageSearch[] getImageSearch(String keyword) {
        return this.getImageRepository().getSearch(keyword);
    }

    public ImageCreated createImage(String image) {
        return this.getImageRepository().postCreate(image);
    }

    protected ImageRepository getImageRepository() {
        return imageRepository;
    }
}
