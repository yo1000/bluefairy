package com.yo1000.bluefairy.model.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by yoichi.kikuchi on 15/05/07.
 */
public class UserDetailsWithSalt extends org.springframework.security.core.userdetails.User {
    private String salt;

    public UserDetailsWithSalt(String username, String password, String salt, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.setSalt(salt);
    }

    public UserDetailsWithSalt(String username, String password, String salt, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.setSalt(salt);
    }

    public String getSalt() {
        return salt;
    }

    protected void setSalt(String salt) {
        this.salt = salt;
    }
}
