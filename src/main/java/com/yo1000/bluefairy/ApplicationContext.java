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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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

    public static final String PROPS_DATA = "bluefairy.data";
    public static final String PROPS_DATA_JDBC = "bluefairy.data.jdbc";
    public static final String PROPS_DATA_MONGO = "bluefairy.data.mongo";
    public static final String PROPS_DATA_TYPE = "bluefairy.data.type";
    public static final String PROPS_DATA_TYPE_JDBC = "JDBC";
    public static final String PROPS_DATA_TYPE_MONGO = "MONGO";
    public static final String PROPS_DOCKER = "bluefairy.docker";

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
    @ConditionalOnProperty(name = PROPS_DATA_TYPE, havingValue = PROPS_DATA_TYPE_JDBC)
    public JdbcTemplate jdbcTemplate(
            @Qualifier("jdbcConfiguration") JdbcConfiguration jdbcConfiguration
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcConfiguration.getDriverClassName());
        dataSource.setUrl(jdbcConfiguration.getUrl());
        dataSource.setUsername(jdbcConfiguration.getUsername());
        dataSource.setPassword(jdbcConfiguration.getPassword());

        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Autowired
    @ConditionalOnProperty(name = PROPS_DATA_TYPE, havingValue = PROPS_DATA_TYPE_MONGO)
    public MongoTemplate mongoTemplate(
            @Qualifier("mongoConfiguration") MongoConfiguration mongoConfiguration
    ) throws Exception {
        MongoClient client = new MongoClient(new ServerAddress(
                mongoConfiguration.getHost(),
                mongoConfiguration.getPort()
        ));

        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(client, mongoConfiguration.getDatabase(),
                new UserCredentials(mongoConfiguration.getUsername(), mongoConfiguration.getPassword()));

        return new MongoTemplate(mongoDbFactory);
    }

    @Bean
    @ConfigurationProperties(prefix = PROPS_DATA)
    public DataSourceConfiguration dataSourceConfiguration() {
        return new DataSourceConfiguration();
    }

    @Bean
    @ConfigurationProperties(prefix = PROPS_DATA_JDBC)
    public JdbcConfiguration jdbcConfiguration() {
        return new JdbcConfiguration();
    }

    @Bean
    @ConfigurationProperties(prefix = PROPS_DATA_MONGO)
    public MongoConfiguration mongoConfiguration() {
        return new MongoConfiguration();
    }

    @Bean
    @ConfigurationProperties(prefix = PROPS_DOCKER)
    public DockerConfiguration dockerConfiguration() {
        return new DockerConfiguration();
    }

    public static enum DataSourceType {
        JDBC(JdbcTemplate.class),
        MONGO(MongoTemplate.class);

        private Class<?> templateClass;

        DataSourceType(Class<?> templateClass) {
            this.templateClass = templateClass;
        }

        public Class<?> getTemplateClass() {
            return templateClass;
        }
    }

    @SpringBootApplication
    public static class DataSourceConfiguration {
        private DataSourceType type = DataSourceType.JDBC;

        public DataSourceType getType() {
            return type;
        }

        public void setType(DataSourceType type) {
            this.type = type;
        }
    }

    @SpringBootApplication
    public static class JdbcConfiguration {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private String schema;

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }
    }

    @SpringBootApplication
    public static class MongoConfiguration {
        private String host = "";
        private int port = 27017;
        private String database;
        private String username;
        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
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
}
