package com.yo1000.bluefairy.model.repository.jdbc;

import com.yo1000.bluefairy.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by yoichi.kikuchi on 15/06/01.
 */
@Repository
@ConditionalOnProperty(name = ApplicationContext.PROPS_DATA_TYPE, havingValue = ApplicationContext.PROPS_DATA_TYPE_JDBC)
public class JdbcInitializeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private boolean initialized;

    public void triesInitialize(Exception e) {
        if (this.isInitialized()) {
            throw new IllegalStateException(e);
        }

        this.getJdbcTemplate().execute("CREATE TABLE APPLICATION_USER (" +
                        "USER_ID  VARCHAR(64) NOT NULL, " +
                        "USERNAME VARCHAR(32) NOT NULL UNIQUE, " +
                        "PASSWORD VARCHAR(64) NOT NULL, " +
                        "SALT     VARCHAR(64) NOT NULL, " +
                        "ROLE     VARCHAR(32) NOT NULL, " +
                        "FULLNAME VARCHAR(256) NOT NULL, " +
                        "PRIMARY KEY(USER_ID))"
        );

        this.getJdbcTemplate().execute("CREATE TABLE CONTAINER_CREATOR (" +
                        "CREATOR_ID VARCHAR(64) NOT NULL, " +
                        "USER_ID    VARCHAR(64) NOT NULL, " +
                        "PRIMARY KEY(CREATOR_ID))"
        );

        this.setInitialized(true);
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected boolean isInitialized() {
        return initialized;
    }

    protected void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
