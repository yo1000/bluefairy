package com.yo1000.bluefairy.model.repository.mongo;

import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/22.
 */
@Repository
public class MongoUserRepository implements UserRepository {
    @Resource
    private MongoTemplate mongoTemplate;

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
                .addToSet("password", user.getPassword())
                .addToSet("role", user.getRole()),
                User.class);
    }

    @Override
    public boolean exists(String username) {
        return this.getMongoTemplate().exists(Query.query(Criteria
                .where("username").is(username)),
                User.class);
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return this.getMongoTemplate().findOne(Query.query(Criteria
                .where("username").is(username)),
                User.class);
    }

    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
