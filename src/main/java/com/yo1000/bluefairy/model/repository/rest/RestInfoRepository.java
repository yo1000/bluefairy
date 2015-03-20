package com.yo1000.bluefairy.model.repository.rest;

import com.yo1000.bluefairy.model.entity.docker.Info;
import com.yo1000.bluefairy.model.repository.InfoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Repository
public class RestInfoRepository extends AbstractRestDockerRepository
        implements InfoRepository {
    @Override
    public Info getJson() {
        return this.getRestTemplate().getForObject(
                this.makeDockerRemoteApiEndpoint("info"),
                Info.class);
    }
}
