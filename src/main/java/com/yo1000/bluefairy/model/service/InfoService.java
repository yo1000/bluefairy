package com.yo1000.bluefairy.model.service;

import com.yo1000.bluefairy.model.entity.docker.Info;
import com.yo1000.bluefairy.model.repository.InfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Service
public class InfoService {
    @Resource
    private InfoRepository infoRepository;

    public Info getInfo() {
        return this.getInfoRepository().getJson();
    }

    protected InfoRepository getInfoRepository() {
        return infoRepository;
    }
}
