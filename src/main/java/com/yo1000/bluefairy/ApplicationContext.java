package com.yo1000.bluefairy;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 * Created by yoichi.kikuchi on 15/02/22.
 */
@SpringBootApplication
public class ApplicationContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);
    public static final String APPLICATION_DATA_TYPE = "application.data.type";
    public static final String APPLICATION_DATA_TYPE_JDBC = "JDBC";
    public static final String APPLICATION_DATA_TYPE_MONGO = "MONGO";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationContext.class, args);
    }

    @Bean
    @Autowired
    public RestTemplate restTemplate(@Qualifier("httpClient") HttpClient httpClient) throws Exception {
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
    @ConditionalOnProperty(value = APPLICATION_DATA_TYPE, havingValue = APPLICATION_DATA_TYPE_JDBC)
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnProperty(value = APPLICATION_DATA_TYPE, havingValue = APPLICATION_DATA_TYPE_MONGO)
    @Autowired
    public MongoTemplate mongoTemplate(MongoProperties properties) throws Exception {
        MongoClient client = new MongoClient(new ServerAddress(
            properties.getHost(), properties.getPort()));

        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(client, properties.getDatabase(),
            new UserCredentials(properties.getUsername(), new String(properties.getPassword())));

        return new MongoTemplate(mongoDbFactory);
    }

    @Bean
    @ConfigurationProperties(prefix = "application.docker")
    public DockerProperties dockerConfiguration() {
        return new DockerProperties();
    }

    public static class DockerProperties {
        private String remoteApi = "http://localhost:2376/";

        public String getRemoteApi() {
            return remoteApi;
        }

        public void setRemoteApi(String remoteApi) {
            this.remoteApi = remoteApi;
        }
    }
}
