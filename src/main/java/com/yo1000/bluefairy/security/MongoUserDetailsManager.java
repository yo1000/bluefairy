package com.yo1000.bluefairy.security;

import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/22.
 */
public class MongoUserDetailsManager implements UserDetailsManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoUserDetailsManager.class);
    private AuthenticationManager authenticationManager;

    @Resource
    private UserRepository userRepository;

    public MongoUserDetailsManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        Assert.isTrue(!this.getUserRepository().exists(userDetails.getUsername()));

        this.getUserRepository().create(userDetails);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        Assert.isTrue(this.getUserRepository().exists(userDetails.getUsername()));

        this.getUserRepository().update(userDetails);
    }

    @Override
    public void deleteUser(String username) {
        this.getUserRepository().delete(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException("Can't change password as no Authentication object found in context " +
                    "for current user.");
        }

        String username = currentUser.getName();

        logger.debug("Changing password for user '" + username + "'");

        // If an authentication manager has been set, re-authenticate the user with the supplied password.
        if (this.getAuthenticationManager() != null) {
            logger.debug("Reauthenticating user '" + username + "' for password change request.");

            this.getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            logger.debug("No authentication manager set. Password won't be re-checked.");
        }

        UserDetails userDetails = this.getUserRepository().findByUsername(username);

        if (userDetails == null) {
            throw new IllegalStateException("Current user doesn't exist in database.");
        }

        Assert.isInstanceOf(User.class, userDetails);

        User identUser = (User) userDetails;
        identUser.setPassword(newPassword);
        this.getUserRepository().update(identUser);
    }

    @Override
    public boolean userExists(String username) {
        return this.getUserRepository().exists(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.getUserRepository().findByUsername(username);
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
