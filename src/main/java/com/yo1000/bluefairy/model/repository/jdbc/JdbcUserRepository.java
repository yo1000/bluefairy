package com.yo1000.bluefairy.model.repository.jdbc;

import com.yo1000.bluefairy.ApplicationContext;
import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by yoichi.kikuchi on 15/05/31.
 */
@Repository
@ConditionalOnProperty(name = ApplicationContext.PROPS_DATA_TYPE, havingValue = ApplicationContext.PROPS_DATA_TYPE_JDBC)
public class JdbcUserRepository implements UserRepository {
    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcInitializeRepository initializeRepository;

    private ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);

    @Override
    public boolean exists(String username) {
        try {
            return this.getJdbcTemplate().queryForObject(
                    "SELECT COUNT(USER_ID) AS USER_COUNT " +
                            "FROM APPLICATION_USER " +
                            "WHERE USERNAME=?",
                    Integer.class,
                    username
            ) > 0;
        }
        catch (Exception e) {
            this.getInitializeRepository().triesInitialize(e);
            return this.exists(username);
        }
    }

    @Override
    public long count() {
        try {
            return this.getJdbcTemplate().queryForObject(
                    "SELECT COUNT(USER_ID) AS USER_COUNT " +
                            "FROM APPLICATION_USER",
                    Long.class
            );
        }
        catch (Exception e) {
            this.getInitializeRepository().triesInitialize(e);
            return this.count();
        }
    }

    @Override
    public User[] find() {
        try {
            return this.getJdbcTemplate().query(
                    "SELECT " +
                            "USER_ID  AS ID, " +
                            "USERNAME AS USERNAME, " +
                            "PASSWORD AS PASSWORD, " +
                            "SALT     AS SALT, " +
                            "ROLE     AS ROLE, " +
                            "FULLNAME AS FULLNAME " +
                            "FROM APPLICATION_USER",
                    new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new User(
                                    resultSet.getString("ID"),
                                    resultSet.getString("USERNAME"),
                                    resultSet.getString("PASSWORD"),
                                    resultSet.getString("SALT"),
                                    resultSet.getString("ROLE"),
                                    resultSet.getString("FULLNAME")
                            );
                        }
                    }).toArray(new User[]{}
            );
        }
        catch (Exception e) {
            this.getInitializeRepository().triesInitialize(e);
            return this.find();
        }
    }

    @Override
    public User findById(String id) {
        try {
            return this.getJdbcTemplate().queryForObject(
                    "SELECT " +
                            "USER_ID  AS ID, " +
                            "USERNAME AS USERNAME, " +
                            "PASSWORD AS PASSWORD, " +
                            "SALT     AS SALT, " +
                            "ROLE     AS ROLE, " +
                            "FULLNAME AS FULLNAME " +
                            "FROM APPLICATION_USER " +
                            "WHERE USER_ID=?",
                    new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new User(
                                    resultSet.getString("ID"),
                                    resultSet.getString("USERNAME"),
                                    resultSet.getString("PASSWORD"),
                                    resultSet.getString("SALT"),
                                    resultSet.getString("ROLE"),
                                    resultSet.getString("FULLNAME")
                            );
                        }
                    },
                    id
            );
        }
        catch (Exception e) {
            this.getInitializeRepository().triesInitialize(e);
            return this.findById(id);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return this.getJdbcTemplate().queryForObject(
                    "SELECT " +
                            "USER_ID  AS ID, " +
                            "USERNAME AS USERNAME, " +
                            "PASSWORD AS PASSWORD, " +
                            "SALT     AS SALT, " +
                            "ROLE     AS ROLE, " +
                            "FULLNAME AS FULLNAME " +
                            "FROM APPLICATION_USER " +
                            "WHERE USERNAME=?",
                    new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new User(
                                    resultSet.getString("ID"),
                                    resultSet.getString("USERNAME"),
                                    resultSet.getString("PASSWORD"),
                                    resultSet.getString("SALT"),
                                    resultSet.getString("ROLE"),
                                    resultSet.getString("FULLNAME")
                            );
                        }
                    },
                    username
            );
        }
        catch (Exception e) {
            this.getInitializeRepository().triesInitialize(e);
            return this.findByUsername(username);
        }
    }

    @Override
    public void create(User user) {
        try {
            this.getJdbcTemplate().update(
                    "INSERT INTO APPLICATION_USER(" +
                            "USER_ID, USERNAME, PASSWORD, " +
                            "SALT   , ROLE    , FULLNAME" +
                            ") VALUES (?, ?, ?, ?, ?, ?)",
                    this.getShaPasswordEncoder().encodePassword(
                            UUID.randomUUID().toString(), ""),
                    user.getUsername(),
                    user.getPassword(),
                    user.getSalt(),
                    user.getRole(),
                    user.getFullname()
            );
        }
        catch (Exception e) {
            this.getInitializeRepository().triesInitialize(e);
            this.create(user);
        }
    }

    @Override
    public void update(User user) {
        try {
            this.getJdbcTemplate().update(
                    "UPDATE APPLICATION_USER SET " +
                            "USERNAME=?, PASSWORD=?, SALT=?, ROLE=?, FULLNAME=? " +
                            "WHERE USER_ID=?",
                    user.getUsername(),
                    user.getPassword(),
                    user.getSalt(),
                    user.getRole(),
                    user.getFullname(),
                    user.getId()
            );
        }
        catch (Exception e) {
            this.getInitializeRepository().triesInitialize(e);
            this.update(user);
        }
    }

    @Override
    public void delete(String username) {
        try {
            this.getJdbcTemplate().update(
                    "DELETE FROM APPLICATION_USER WHERE USER_ID=?",
                    username
            );
        }
        catch (Exception e) {
            this.getInitializeRepository().triesInitialize(e);
            this.delete(username);
        }
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected JdbcInitializeRepository getInitializeRepository() {
        return initializeRepository;
    }

    protected ShaPasswordEncoder getShaPasswordEncoder() {
        return shaPasswordEncoder;
    }
}
