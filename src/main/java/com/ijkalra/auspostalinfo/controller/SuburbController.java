package com.ijkalra.auspostalinfo.controller;

import com.ijkalra.auspostalinfo.entity.Suburb;
import com.ijkalra.auspostalinfo.response.PostCodeDetails;
import com.ijkalra.auspostalinfo.service.PostalInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/suburb")
@RestController
public class SuburbController {

    Logger logger = LoggerFactory.getLogger(SuburbController.class);

    @Autowired
    private PostalInfoService postalInfoService;

    @GetMapping("/")
    public PostCodeDetails getPostCodeBySuburbNameAndState(@RequestParam String suburbname, @RequestParam String stateabbr){
        logger.info("Incoming request to get postcode for suburb and state combination");
        return postalInfoService.getPostCodeBySuburbName(suburbname, stateabbr);
    }

    @PostMapping("/newsuburb")
    public String createNewSuburbProfile(@RequestBody Suburb suburb) {
        logger.info("Incoming request to create new suburb entry");
        String suburbId = postalInfoService.saveToDb(suburb);
        if (suburbId == null) {
            // Todo: send bad request error here
            logger.error("Suburb not saved in database");
            return null;
        }
        else {
            logger.info("Returning suburb id of the newly created suburb to the caller");
            return suburbId;
        }
    }
}
