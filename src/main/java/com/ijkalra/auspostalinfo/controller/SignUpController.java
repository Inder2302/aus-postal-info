package com.ijkalra.auspostalinfo.controller;

import com.ijkalra.auspostalinfo.entity.User;
import com.ijkalra.auspostalinfo.response.SuburbDetails;
import com.ijkalra.auspostalinfo.service.SignUpService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        logger.info("Incoming request for signing up new user: {} - {}",user.getUsername(), user.getRoles());
        User newUser = signUpService.saveNewUser(user);
        if (newUser == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
