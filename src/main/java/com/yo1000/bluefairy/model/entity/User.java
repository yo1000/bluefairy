package com.yo1000.bluefairy.model.entity;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by yoichi.kikuchi on 15/03/24.
 */
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique=true)
    private String username;
    private String password;
    private String role;

    public User() {}

    public User(ObjectId id, String username, String password, String role) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
