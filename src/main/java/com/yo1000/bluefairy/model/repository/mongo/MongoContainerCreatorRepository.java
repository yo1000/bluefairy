package com.yo1000.bluefairy.model.repository.mongo;

import com.yo1000.bluefairy.ApplicationContext;
import com.yo1000.bluefairy.model.entity.ContainerCreator;
import com.yo1000.bluefairy.model.repository.ContainerCreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yoichi.kikuchi on 15/05/28.
 */
@Repository
@ConditionalOnProperty(
    name = ApplicationContext.APPLICATION_DATA_TYPE,
    havingValue = ApplicationContext.APPLICATION_DATA_TYPE_MONGO)
public class MongoContainerCreatorRepository implements ContainerCreatorRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ContainerCreator findById(String id) {
        return this.getMongoTemplate().findOne(Query.query(Criteria
                .where("id").is(id)), ContainerCreator.class);
    }

    @Override
    public List<ContainerCreator> find() {
        return this.getMongoTemplate().findAll(ContainerCreator.class);
    }

    @Override
    public void create(ContainerCreator containerCreator) {
        this.getMongoTemplate().insert(containerCreator);
    }

    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
