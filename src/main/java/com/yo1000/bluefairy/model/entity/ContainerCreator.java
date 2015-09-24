package com.yo1000.bluefairy.model.entity;

import com.yo1000.bluefairy.model.entity.docker.Container;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yoichi.kikuchi on 15/05/28.
 */
@Document
public class ContainerCreator {
    @Id
    private String id;
    @DBRef
    private User creator;
    @Transient
    private Container container;

    public ContainerCreator() {}

    public ContainerCreator(String id, User creator) {
        this.id = id;
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
