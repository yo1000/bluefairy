package com.yo1000.bluefairy.model.repository.mongo;

import com.yo1000.bluefairy.ApplicationContext;
import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Created by yoichi.kikuchi on 15/03/22.
 */
@Repository
@ConditionalOnProperty(
    name = ApplicationContext.APPLICATION_DATA_TYPE,
    havingValue = ApplicationContext.APPLICATION_DATA_TYPE_MONGO)
public class MongoUserRepository implements UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean exists(String username) {
        return this.getMongoTemplate().exists(Query.query(Criteria
                        .where("username").is(username)),
                User.class);
    }

    @Override
    public long count() {
        return this.getMongoTemplate().count(new Query(), User.class);
    }

    @Override
    public User[] find() {
        return this.getMongoTemplate().findAll(User.class).toArray(new User[] {});
    }

    @Override
    public User findById(String id) {
        return this.getMongoTemplate().findOne(Query.query(Criteria
                        .where("id").is(id)),
                User.class);
    }

    @Override
    public User findByUsername(String username) {
        return this.getMongoTemplate().findOne(Query.query(Criteria
                        .where("username").is(username)),
                User.class);
    }

    @Override
    public void create(User user) {
        Assert.isInstanceOf(User.class, user);

        this.getMongoTemplate().insert(user);
    }

    @Override
    public void delete(String username) {
        this.getMongoTemplate().remove(Query.query(Criteria
                        .where("username").is(username)),
                User.class);
    }

    @Override
    public void update(User user) {
        this.getMongoTemplate().updateFirst(Query.query(Criteria
                .where("id").is(user.getId())), Update
                .update("username", user.getUsername())
                .set("password", user.getPassword())
                .set("role", user.getRole())
                .set("fullname", user.getFullname()),
                User.class);
    }

    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
