package com.ijkalra.auspostalinfo.service;

import com.ijkalra.auspostalinfo.entity.CustomUserDetails;

import com.ijkalra.auspostalinfo.entity.User;
import com.ijkalra.auspostalinfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    // this service bean is autowired in security configuration
    // we need to override this method which is used by AuthenticationManager
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        // user name is not found in db
        user.orElseThrow(() -> new UsernameNotFoundException("Username Not found in DB"));
        return user.map(CustomUserDetails::new).get();
    }
}
