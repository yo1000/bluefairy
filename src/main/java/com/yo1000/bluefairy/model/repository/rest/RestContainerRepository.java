package com.yo1000.bluefairy.model.repository.rest;

import com.yo1000.bluefairy.model.entity.docker.Container;
import com.yo1000.bluefairy.model.entity.docker.ContainerCreate;
import com.yo1000.bluefairy.model.entity.docker.ContainerCreated;
import com.yo1000.bluefairy.model.entity.docker.ContainerInspect;
import com.yo1000.bluefairy.model.repository.ContainerRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yoichi.kikuchi on 15/03/12.
 */
@Repository
public class RestContainerRepository extends AbstractRestDockerRepository
        implements ContainerRepository {
    @Override
    public Container[] getJson() {
        return this.getJson(false);
    }

    @Override
    public Container[] getJson(boolean all) {
        return this.getRestTemplate().getForObject(
                this.makeDockerRemoteApiEndpoint("containers/json?all={all}"),
                Container[].class,
                all
        );
    }

    @Override
    public ContainerInspect getInspect(String id) {
        return this.getRestTemplate().getForObject(
                this.makeDockerRemoteApiEndpoint("containers/{id}/json"),
                ContainerInspect.class,
                id
        );
    }

    @Override
    public ContainerCreated postCreate(ContainerCreate containerCreate) {
        return this.postCreate(containerCreate, null);
    }

    @Override
    public ContainerCreated postCreate(ContainerCreate containerCreate, String name) {
        return this.getRestTemplate().postForObject(
                this.makeDockerRemoteApiEndpoint("containers/create" +
                        (name != null && !name.isEmpty() ? "?name={name}" : "")),
                containerCreate,
                ContainerCreated.class,
                name
        );
    }

    @Override
    public void postStart(String id) {
        this.getRestTemplate().postForLocation(
                this.makeDockerRemoteApiEndpoint("containers/{id}/start"),
                null,
                id
        );
    }

    @Override
    public void postStop(String id) {
        this.postStop(id, 0L);
    }

    @Override
    public void postStop(String id, long wait) {
        this.getRestTemplate().postForLocation(
                this.makeDockerRemoteApiEndpoint("containers/{id}/stop?t={wait}"),
                null,
                id, wait
        );
    }

    @Override
    public void deleteRemove(String id) {
        this.deleteRemove(id, false, false);
    }

    @Override
    public void deleteRemove(String id, boolean volume, boolean force) {
        this.getRestTemplate().delete(
                this.makeDockerRemoteApiEndpoint("containers/{id}?v={volume}&force={force}"),
                id, volume, force
        );
    }
}
