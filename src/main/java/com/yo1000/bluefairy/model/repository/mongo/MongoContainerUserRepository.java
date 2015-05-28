package com.yo1000.bluefairy.model.repository.mongo;

import com.yo1000.bluefairy.model.entity.ContainerUser;
import com.yo1000.bluefairy.model.repository.ContainerUserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yoichi.kikuchi on 15/05/28.
 */
@Repository
public class MongoContainerUserRepository implements ContainerUserRepository {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public ContainerUser findById(String id) {
        return this.getMongoTemplate().findOne(Query.query(Criteria
                .where("id").is(id)), ContainerUser.class);
    }

    @Override
    public List<ContainerUser> find() {
        return this.getMongoTemplate().findAll(ContainerUser.class);
    }

    @Override
    public void create(ContainerUser containerUser) {
        this.getMongoTemplate().insert(containerUser);
    }

    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
