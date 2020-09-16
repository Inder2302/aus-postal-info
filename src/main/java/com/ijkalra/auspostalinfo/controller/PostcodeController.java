package com.ijkalra.auspostalinfo.controller;

import com.ijkalra.auspostalinfo.exception.ResourceNotFoundException;
import com.ijkalra.auspostalinfo.response.SuburbDetails;
import com.ijkalra.auspostalinfo.service.PostalInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postcode")
public class PostcodeController {

    Logger logger = LoggerFactory.getLogger(PostcodeController.class);

    @Autowired
    private PostalInfoService postalInfoService;

    @GetMapping("/{postcode}")
    public SuburbDetails getPostCodeInfo(@PathVariable("postcode") int postcode) {
        logger.info("Incoming request to get info for a post code");
        SuburbDetails suburbDetails = postalInfoService.getSuburbsByPostCode(postcode);
        if(suburbDetails.isFound()) {
            return suburbDetails;
        }
        else {
            throw new ResourceNotFoundException("Post code: " + postcode + " not Found in database");
        }
    }

}
