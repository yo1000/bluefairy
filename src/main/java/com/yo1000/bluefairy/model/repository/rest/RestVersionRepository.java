package com.yo1000.bluefairy.model.repository.rest;

import com.yo1000.bluefairy.model.entity.docker.Version;
import com.yo1000.bluefairy.model.repository.VersionRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Repository
public class RestVersionRepository extends AbstractRestDockerRepository implements VersionRepository {
    @Override
    public Version getJson() {
        return this.getRestTemplate().getForObject(
                this.makeDockerRemoteApiEndpoint("version"),
                Version.class);
    }
}
