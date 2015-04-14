package com.yo1000.bluefairy;

import com.yo1000.bluefairy.model.service.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/21.
 */
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class Security extends WebSecurityConfigurerAdapter {
    @Resource
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("")
                .defaultSuccessUrl("")
                .failureUrl("")
                .and()
                .logout()
                .logoutUrl("")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.getUserService())
                .passwordEncoder(new ShaPasswordEncoder(256));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("static/**");
    }

    protected UserService getUserService() {
        return userService;
    }
}
