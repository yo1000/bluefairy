package com.yo1000.bluefairy;

import com.yo1000.bluefairy.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/03/21.
 */
@Configuration
@EnableWebMvcSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityContext extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Autowired
    private SaltSource saltSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .regexMatchers("^/auth/login.*").permitAll()
                .regexMatchers("^/auth/register.*").permitAll()
                .regexMatchers("^/container.*").hasAnyAuthority("ADMIN", "DEVEL", "USER")
                .regexMatchers("^/image.*").hasAnyAuthority("ADMIN", "DEVEL")
                .regexMatchers("^/search.*").hasAnyAuthority("ADMIN", "DEVEL")
                .regexMatchers("^/info.*").hasAnyAuthority("ADMIN", "DEVEL", "USER")
                .regexMatchers("^/version.*").hasAnyAuthority("ADMIN", "DEVEL", "USER")
                .regexMatchers("^/user/$").hasAnyAuthority("ADMIN")
                .regexMatchers("^/user/(?!(?:all|register|update)).+").hasAnyAuthority("ADMIN")
                .regexMatchers("^/user/register.*").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/auth/login");

        http.logout()
                .logoutUrl("/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/auth/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.getUserService());
        provider.setPasswordEncoder(this.getShaPasswordEncoder());
        provider.setSaltSource(this.getSaltSource());

        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .regexMatchers("^/resources/.*");
    }

    protected UserService getUserService() {
        return userService;
    }

    protected ShaPasswordEncoder getShaPasswordEncoder() {
        return shaPasswordEncoder;
    }

    protected SaltSource getSaltSource() {
        return saltSource;
    }
}
