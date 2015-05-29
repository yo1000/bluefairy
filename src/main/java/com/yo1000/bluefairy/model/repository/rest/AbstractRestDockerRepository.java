package com.yo1000.bluefairy.model.repository.rest;

import com.yo1000.bluefairy.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
public class AbstractRestDockerRepository {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ApplicationContext.DockerConfiguration dockerConfiguration;

    protected String makeDockerRemoteApiEndpoint(String resource) {
        String baseUrl = this.getDockerConfiguration().getRemoteApi();
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        String resourceName = resource;
        if (resourceName.startsWith("/")) {
            resourceName = resourceName.substring(1);
        }

        return baseUrl + resourceName;
    }

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

    protected ApplicationContext.DockerConfiguration getDockerConfiguration() {
        return dockerConfiguration;
    }
}
