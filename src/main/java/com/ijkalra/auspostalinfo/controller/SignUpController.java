package com.ijkalra.auspostalinfo.controller;

import com.ijkalra.auspostalinfo.entity.User;
import com.ijkalra.auspostalinfo.response.SuburbDetails;
import com.ijkalra.auspostalinfo.service.SignUpService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    SignUpService signUpService;

    @ApiOperation(value = "Sign up a new user",
            notes = "Provide user details to add to the user database",
            response = Integer.class
    )
    @PostMapping("/signup")
    public int createUser(@RequestBody User user) {
        logger.info("Incoming request for signing up new user: {} - {}",user.getUsername(), user.getRoles());
        return signUpService.saveNewUser(user);
    }
}
