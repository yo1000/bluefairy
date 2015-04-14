package com.yo1000.bluefairy.model.repository;

import com.yo1000.bluefairy.model.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by yoichi.kikuchi on 15/03/22.
 */
public interface UserRepository {
    public void create(User user);
    public void delete(String username);
    public void update(User user);
    public boolean exists(String username);
    public User findByUsername(String username) throws UsernameNotFoundException;
}
