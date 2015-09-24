package com.yo1000.bluefairy.model.repository;

import com.yo1000.bluefairy.model.entity.ContainerCreator;

import java.util.List;

/**
 * Created by yoichi.kikuchi on 15/05/28.
 */
public interface ContainerCreatorRepository {
    boolean existsByIdAndUsername(String id, String username);
    ContainerCreator findById(String id);
    List<ContainerCreator> find();
    void create(ContainerCreator containerCreator);
}
