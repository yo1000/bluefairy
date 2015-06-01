package com.yo1000.bluefairy.model.repository;

import com.yo1000.bluefairy.model.entity.ContainerCreator;

import java.util.List;

/**
 * Created by yoichi.kikuchi on 15/05/28.
 */
public interface ContainerCreatorRepository {
    public ContainerCreator findById(String id);
    public List<ContainerCreator> find();
    public void create(ContainerCreator containerCreator);
}
