package com.yo1000.bluefairy;

import com.mongodb.Mongo;
import com.mongodb.MongoURI;
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
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public DockerConfiguration dockerConfiguration() {
        return new DockerConfiguration();
    }

    @Bean
    public RestTemplate restTemplate() throws Exception {
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

    @Bean
    public ShaPasswordEncoder shaPasswordEncoder() {
        return new ShaPasswordEncoder(256);
    }

    @Bean
    public SaltSource saltSource() {
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("username");

        return saltSource;
    }

    @Bean
    public MongoConfiguration mongoConfiguration() {
        return new MongoConfiguration();
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(this.mongo(),
                this.mongoConfiguration().getDatabase(), new UserCredentials(
                this.mongoConfiguration().getUsername(),
                this.mongoConfiguration().getPassword()));
    }

    @Bean
    public Mongo mongo() throws Exception {
        return new Mongo(new MongoURI(this.mongoConfiguration().getUri()));
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

    @ConfigurationProperties(prefix = "bluefairy.mongo")
    public static class MongoConfiguration {
        private String uri = "mongodb://localhost:27017/";
        private String database = "bluefairy";
        private String username = "admin";
        private String password = "password";

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
