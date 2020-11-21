package com.example.demo.config;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    //private final String role = "ADMIN";

    @Autowired
    private UserService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .authorizeRequests()
                .antMatchers("/userList").access();
        */http
                .authorizeRequests()
                .antMatchers("/","/registration","/about").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)//.jdbcAuthentication()
                //.dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
                //.usersByUsernameQuery("select username, password, active from user where username=?")
                //.authoritiesByUsernameQuery("select u.username, ur.roles from user u inner join user_role ur on u.id = ur.id_user where u.username=?");

    }
}
