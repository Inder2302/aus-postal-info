package com.ijkalra.auspostalinfo.service;

import com.ijkalra.auspostalinfo.entity.User;
import com.ijkalra.auspostalinfo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    Logger logger = LoggerFactory.getLogger(SignUpService.class);
    @Autowired
    UserRepository userRepository;

    // this bean is present in SecurityConfiguration class
    // it returns BcryptPasswordEncoder
    @Autowired
    PasswordEncoder passwordEncoder;

    public User saveNewUser(User user) {
        // this is sign up service
        // encoding the password before it is saved to db
        logger.info("Encoding the password with Bcrypt encoder");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("Saving new user to the database");
        return userRepository.save(user);
    }
}
