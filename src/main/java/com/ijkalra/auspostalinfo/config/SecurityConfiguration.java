package com.ijkalra.auspostalinfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Provide the user details service to the AuthenticationManager so that it can get call the
        // loadUserByUsername method on it
        // we have autowired it so Spring will actually give our custom bean which implements UserDetailsService - CustomUserDetailsService
        // which has overridden the loadUserByUsername
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable csrf to allow call via postman tool else we will need csrf token
        // use antMatchers to restrict access to url based on roles
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/suburb/newsuburb").hasRole("ADMIN")
                .antMatchers("/suburb/").hasAnyRole("USER", "ADMIN")
                .antMatchers("/postcode").hasAnyRole("USER","ADMIN")
                .antMatchers("/signup").permitAll()
                .and()
                .httpBasic();
    }

    // Define a password encoder bean For password hashing
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //  disable security on H2. H2 has its own username password
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2/**");
    }
}
