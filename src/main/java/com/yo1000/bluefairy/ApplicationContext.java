package com.yo1000.bluefairy;

import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yoichi.kikuchi on 15/02/22.
 */
@SpringBootApplication
public class ApplicationContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationContext.class, args);
    }

    @Bean
    @Autowired
    public RestTemplate restTemplate(
            @Qualifier("httpClient") HttpClient httpClient
    ) throws Exception {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);

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
        saltSource.setUserPropertyToUse("salt");

        return saltSource;
    }

    @Bean
    @Autowired
    public MongoTemplate mongoTemplate(
            @Qualifier("mongoConfiguration") MongoConfiguration mongoConfiguration,
            @Qualifier("mongo") Mongo mongo
    ) throws Exception {
        return new MongoTemplate(mongo,
                mongoConfiguration.getDatabase(), new UserCredentials(
                mongoConfiguration.getUsername(),
                mongoConfiguration.getPassword()));
    }

    @Bean
    @Autowired
    public Mongo mongo(
            @Qualifier("mongoConfiguration") MongoConfiguration mongoConfiguration
    ) throws Exception {
        return new Mongo(new MongoURI(mongoConfiguration.getUri()));
    }

    @Bean
    @ConfigurationProperties(prefix = "bluefairy.docker")
    public DockerConfiguration dockerConfiguration() {
        return new DockerConfiguration();
    }

    @Bean
    @ConfigurationProperties(prefix = "bluefairy.mongo")
    public MongoConfiguration mongoConfiguration() {
        return new MongoConfiguration();
    }

    @SpringBootApplication
    public static class DockerConfiguration {
        private String remoteApi = "http://localhost:2376/";

        public String getRemoteApi() {
            return remoteApi;
        }

        public void setRemoteApi(String remoteApi) {
            this.remoteApi = remoteApi;
        }
    }

    @SpringBootApplication
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
