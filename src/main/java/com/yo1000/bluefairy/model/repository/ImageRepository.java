package com.yo1000.bluefairy.model.repository;

import com.yo1000.bluefairy.model.entity.docker.Image;
import com.yo1000.bluefairy.model.entity.docker.ImageInspect;
import com.yo1000.bluefairy.model.entity.docker.ImageCreated;
import com.yo1000.bluefairy.model.entity.docker.ImageSearch;

/**
 * Created by yoichi.kikuchi on 15/03/12.
 */
public interface ImageRepository {
    public Image[] getJson();
    public Image[] getJson(boolean all);
    public ImageInspect getInspect(String id);
    public ImageSearch[] getSearch(String keyword);
    public ImageCreated postCreate(String image);
}
