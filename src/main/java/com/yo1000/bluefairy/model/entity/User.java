package com.yo1000.bluefairy.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yoichi.kikuchi on 15/03/24.
 */
@Document
public class User {
    @Id
    private String id;
    @Indexed(unique=true)
    private String username;
    private String password;
    private String role;
    private String salt;
    private String fullname;

    public User() {}

    public User(String id, String username, String password,
                String salt, String role, String fullname) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setSalt(salt);
        this.setRole(role);
        this.setFullname(fullname);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
