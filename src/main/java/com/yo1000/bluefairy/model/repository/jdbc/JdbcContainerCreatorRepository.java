package com.yo1000.bluefairy.model.repository.jdbc;

import com.yo1000.bluefairy.ApplicationContext;
import com.yo1000.bluefairy.model.entity.ContainerCreator;
import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.repository.ContainerCreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yoichi.kikuchi on 15/05/31.
 */
@Repository
@ConditionalOnProperty(
        name = ApplicationContext.APPLICATION_DATA_TYPE,
        havingValue = ApplicationContext.APPLICATION_DATA_TYPE_JDBC)
public class JdbcContainerCreatorRepository implements ContainerCreatorRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsByIdAndUsername(String id, String username) {
        return this.getJdbcTemplate().queryForObject("SELECT " +
                        "  COUNT(CONTAINER_ID) AS ITEM_COUNT " +
                        "FROM " +
                        "  CONTAINER_CREATOR " +
                        "LEFT OUTER JOIN " +
                        "  APPLICATION_USER " +
                        "  ON CONTAINER_CREATOR.USER_ID=APPLICATION_USER.USER_ID " +
                        "WHERE " +
                        "  CONTAINER_ID=? " +
                        "AND " +
                        "  USERNAME=?",
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getInt("ITEM_COUNT");
                    }
                },
                id,
                username
        ) > 0;
    }

    @Override
    public ContainerCreator findById(String id) {
        return this.getJdbcTemplate().queryForObject("SELECT " +
                        "  CONTAINER_ID, " +
                        "  CONTAINER_CREATOR.USER_ID AS USER_ID, " +
                        "  USERNAME, " +
                        "  PASSWORD, " +
                        "  SALT, " +
                        "  ROLE, " +
                        "  FULLNAME " +
                        "FROM " +
                        "  CONTAINER_CREATOR " +
                        "LEFT OUTER JOIN " +
                        "  APPLICATION_USER " +
                        "  ON CONTAINER_CREATOR.USER_ID=APPLICATION_USER.USER_ID " +
                        "WHERE " +
                        "  CONTAINER_ID=?",
                new RowMapper<ContainerCreator>() {
                    @Override
                    public ContainerCreator mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new ContainerCreator(
                                resultSet.getString("CONTAINER_ID"),
                                new User(
                                        resultSet.getString("USER_ID"),
                                        resultSet.getString("USERNAME"),
                                        resultSet.getString("PASSWORD"),
                                        resultSet.getString("SALT"),
                                        resultSet.getString("ROLE"),
                                        resultSet.getString("FULLNAME")
                                )
                        );
                    }
                },
                id
        );
    }

    @Override
    public List<ContainerCreator> find() {
        return this.getJdbcTemplate().query("SELECT " +
                        "  CONTAINER_ID, " +
                        "  CONTAINER_CREATOR.USER_ID AS USER_ID, " +
                        "  USERNAME, " +
                        "  PASSWORD, " +
                        "  SALT, " +
                        "  ROLE, " +
                        "  FULLNAME " +
                        "FROM " +
                        "  CONTAINER_CREATOR " +
                        "LEFT OUTER JOIN " +
                        "  APPLICATION_USER " +
                        "  ON CONTAINER_CREATOR.USER_ID=APPLICATION_USER.USER_ID ",
                new RowMapper<ContainerCreator>() {
                    @Override
                    public ContainerCreator mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new ContainerCreator(
                                resultSet.getString("CONTAINER_ID"),
                                new User(
                                        resultSet.getString("USER_ID"),
                                        resultSet.getString("USERNAME"),
                                        resultSet.getString("PASSWORD"),
                                        resultSet.getString("SALT"),
                                        resultSet.getString("ROLE"),
                                        resultSet.getString("FULLNAME")
                                )
                        );
                    }
                }
        );
    }

    @Override
    public void create(ContainerCreator containerCreator) {
        this.getJdbcTemplate().update("INSERT INTO " +
                        "CONTAINER_CREATOR (CONTAINER_ID, USER_ID) VALUES (?, ?)",
                containerCreator.getId(),
                containerCreator.getCreator().getId());
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
