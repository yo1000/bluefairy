package com.yo1000.bluefairy.model.entity;

import org.apache.commons.codec.digest.DigestUtils;
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
        this(id, username, password, role, false);
    }

    public User(ObjectId id, String username, String password, String role, boolean toSha256) {
        this.setId(id);
        this.setUsername(username);
        this.setRole(role);

        if (toSha256) {
            this.setPasswordToSha256(password);
        }
        else {
            this.setPassword(password);
        }
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
        this.setPassword(password);
    }

    public void setPasswordToSha256(String passwordByCleartext) {
        this.setPassword(DigestUtils.sha256Hex(passwordByCleartext));
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
