package com.yo1000.bluefairy.model.repository.rest;

import com.yo1000.bluefairy.model.repository.InfoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Repository
public class RestInfoRepository extends AbstractRestDockerRepository implements InfoRepository {
    @Override
    public Map<String, Object> getJson() {
        return this.getRestTemplate().getForObject(
                this.makeDockerRemoteApiEndpoint("info"),
                Map.class);
    }
}
