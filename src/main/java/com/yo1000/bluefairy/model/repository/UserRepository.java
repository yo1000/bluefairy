package com.yo1000.bluefairy.model.repository;

import com.yo1000.bluefairy.model.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by yoichi.kikuchi on 15/03/22.
 */
public interface UserRepository {
    public boolean exists(String username);
    public long count();
    public User[] find();
    public User findById(String id);
    public User findByUsername(String username);
    public void create(User user);
    public void update(User user);
    public void delete(String username);
}
