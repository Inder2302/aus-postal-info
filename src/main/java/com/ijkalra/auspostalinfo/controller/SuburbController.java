package com.ijkalra.auspostalinfo.controller;

import com.ijkalra.auspostalinfo.entity.Suburb;
import com.ijkalra.auspostalinfo.exception.ResourceNotFoundException;
import com.ijkalra.auspostalinfo.exception.SuburbAlreadyPresentException;
import com.ijkalra.auspostalinfo.response.PostCodeDetails;
import com.ijkalra.auspostalinfo.service.PostalInfoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/suburb")
@RestController
public class SuburbController {

    Logger logger = LoggerFactory.getLogger(SuburbController.class);

    @Autowired
    private PostalInfoService postalInfoService;

    @GetMapping("/")
    @ApiOperation(value = "finds post code for the suburb and state combination",
            notes = "Provide suburb name and state abbreviation for lookup",
            response = PostCodeDetails.class
    )
    public PostCodeDetails getPostCodeBySuburbNameAndState(@RequestParam String suburbname, @RequestParam String stateabbr){
        logger.info("Incoming request to get postcode for suburb and state combination");
        PostCodeDetails postCodeDetails = postalInfoService.getPostCodeBySuburbName(suburbname, stateabbr);
        if (postCodeDetails.isFound()) {
            return postCodeDetails;
        }
        else {
            throw new ResourceNotFoundException("Suburb: " + suburbname.toUpperCase() + " and state: " + stateabbr.toUpperCase() + " combination not found");
        }
    }

    @ApiOperation(value = "Create a new suburb Entry",
            notes = "Provide new suburb details to save to db",
            response = String.class
    )
    @PostMapping("/newsuburb")
    public String createNewSuburbProfile(@Valid @RequestBody Suburb suburb) {
        logger.info("Incoming request to create new suburb entry");
        String suburbId = postalInfoService.saveToDb(suburb);
        if (suburbId == null) {
            throw new SuburbAlreadyPresentException("The given suburb is already present in Database");
        }
        else {
            logger.info("Returning suburb id of the newly created suburb to the caller");
            return suburbId;
        }
    }
}
