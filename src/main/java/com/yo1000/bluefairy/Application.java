package com.yo1000.bluefairy;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yoichi.kikuchi on 15/02/22.
 */
@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DockerConfiguration docker() {
        return new DockerConfiguration();
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(this.httpClient());

        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClientBuilder.create()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .build();
    }

    @ConfigurationProperties(prefix = "bluefairy.docker")
    public static class DockerConfiguration {
        private String remoteApi = "http://localhost:2376/";

        public String getRemoteApi() {
            return remoteApi;
        }

        public void setRemoteApi(String remoteApi) {
            this.remoteApi = remoteApi;
        }
    }
}
