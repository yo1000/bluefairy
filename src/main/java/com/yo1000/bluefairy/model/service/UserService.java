package com.yo1000.bluefairy.model.service;

import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yoichi.kikuchi on 15/03/24.
 */
@Service
public class UserService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getUserRepository().findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("\"%1$s\" is not found.", username));
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    protected UserRepository getUserRepository() {
        return userRepository;
    }
}
