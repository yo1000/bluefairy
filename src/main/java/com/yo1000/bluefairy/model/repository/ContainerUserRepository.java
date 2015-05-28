package com.yo1000.bluefairy.model.repository;

import com.yo1000.bluefairy.model.entity.ContainerUser;

import java.util.List;

/**
 * Created by yoichi.kikuchi on 15/05/28.
 */
public interface ContainerUserRepository {
    public ContainerUser findById(String id);
    public List<ContainerUser> find();
    public void create(ContainerUser containerUser);
}
