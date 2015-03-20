package com.yo1000.bluefairy.model.service;

import com.yo1000.bluefairy.model.entity.docker.Version;
import com.yo1000.bluefairy.model.repository.VersionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Service
public class VersionService {
    @Resource
    private VersionRepository versionRepository;

    public Version getVersion() {
        return this.getVersionRepository().getJson();
    }

    protected VersionRepository getVersionRepository() {
        return versionRepository;
    }
}
