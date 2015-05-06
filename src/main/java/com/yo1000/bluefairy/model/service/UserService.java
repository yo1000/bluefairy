package com.yo1000.bluefairy.model.service;

import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.repository.UserRepository;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Resource
    private ShaPasswordEncoder shaPasswordEncoder;

    @Resource
    private SaltSource saltSource;

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

    public boolean existsUser() {
        return this.getUserRepository().count() > 0L;
    }

    public void registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.encodePassword(username, password, role));
        user.setRole(role);

        this.getUserRepository().create(user);
    }

    protected String encodePassword(String username, String password, String role) {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username, password, Arrays.asList(new SimpleGrantedAuthority(role)));

        return this.getShaPasswordEncoder().encodePassword(password,
                this.getSaltSource().getSalt(userDetails));
    }

    protected UserRepository getUserRepository() {
        return userRepository;
    }

    protected ShaPasswordEncoder getShaPasswordEncoder() {
        return shaPasswordEncoder;
    }

    protected SaltSource getSaltSource() {
        return saltSource;
    }
}
