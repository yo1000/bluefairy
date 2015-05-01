package com.yo1000.bluefairy.model.repository;

import com.yo1000.bluefairy.model.entity.docker.Container;
import com.yo1000.bluefairy.model.entity.docker.ContainerCreate;
import com.yo1000.bluefairy.model.entity.docker.ContainerCreated;
import com.yo1000.bluefairy.model.entity.docker.ContainerInspect;

/**
 * Created by yoichi.kikuchi on 15/03/12.
 */
public interface ContainerRepository {
    public Container[] getJson();
    public Container[] getJson(boolean all);
    public ContainerInspect getInspect(String id);
    public ContainerCreated postCreate(ContainerCreate containerCreate);
    public ContainerCreated postCreate(ContainerCreate containerCreate, String name);
    public void postStart(String id);
    public void postStop(String id);
    public void postStop(String id, long wait);
    public void deleteRemove(String id);
    public void deleteRemove(String id, boolean volume, boolean force);
}
