package com.ijkalra.auspostalinfo.service;

import com.ijkalra.auspostalinfo.entity.States;
import com.ijkalra.auspostalinfo.entity.Suburb;
import com.ijkalra.auspostalinfo.repository.SuburbRepo;
import com.ijkalra.auspostalinfo.response.PostCodeDetails;
import com.ijkalra.auspostalinfo.response.SuburbDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PostalInfoService {
    Logger logger = LoggerFactory.getLogger(PostalInfoService.class);

    @Autowired
    private SuburbRepo suburbRepo;


    public PostCodeDetails getPostCodeBySuburbName(String suburbName, String stateAbbr) {
        // create response object
        PostCodeDetails postCodeDetails = new PostCodeDetails();

        //lookup data in db
        logger.info("Fetch suburb info from database based on suburb name and state abbreviation");
        Suburb suburb = suburbRepo.findBySuburbNameIgnoreCaseAndStateAbbrIgnoreCase(suburbName, stateAbbr);

        //fill the response object with data from db

        // found = true -> if suburb !=null
        if (suburb == null){
            // we can as well throw an exception here and respond with HTTP error code 4xx
            logger.error("Suburb not found. Returning negative response to the caller");
            postCodeDetails.setFound(false);
            postCodeDetails.setPostcode(-1);
            postCodeDetails.setSuburbName("NOT_FOUND");
        }
        else {
            logger.info("Suburb found in database. Returning Response Entity to the caller");
            postCodeDetails.setFound(true);
            postCodeDetails.setPostcode(suburb.getPostCode());
            postCodeDetails.setSuburbName(suburb.getSuburbName());
        }
        return postCodeDetails;
    }

    public SuburbDetails getSuburbsByPostCode(int postcode) {
        // Create response type object
        SuburbDetails suburbDetails = new SuburbDetails();

        //look up in database via repository function
        logger.info("Lookup post code in database");

        List<Suburb> suburbsByPostCode = suburbRepo.findByPostCode(postcode);

        // loop in the list of suburbs found in db and set the full name of state
        // as derived from State Enum
        logger.info("Look up abbreviation Enum and set full name of state");
        suburbsByPostCode.forEach(s -> s.setState(States.valueOf(s.getStateAbbr().toUpperCase()).getFullName()));

        // fill response object
        suburbDetails.setResult(suburbsByPostCode);
        suburbDetails.setFound(!suburbDetails.getResult().isEmpty());

        logger.info("Return response entity to caller");
        // return response object
        return suburbDetails;
    }

    public String saveToDb(Suburb suburb) {

        logger.info("Check if suburb is not already present in db");
        // Validate if the suburb+state combination is already there in DB
        // Todo: we can add more validations like a postcode in Victoria must be from a given range 3000-3999
        //  or certain postcodes can be marked as reserved and hence should not be used

        if (suburbRepo.findBySuburbNameIgnoreCaseAndStateAbbrIgnoreCase(suburb.getSuburbName(), suburb.getStateAbbr()) == null){

            logger.info("Suburb not present in db. Saving new suburb information...");
            // save Suburb with title case
            suburb.setSuburbName(StringUtils.capitalize(suburb.getSuburbName()));

            // save State with upper case to match later with our enum
            suburb.setStateAbbr(suburb.getStateAbbr().toUpperCase());
            suburbRepo.save(suburb);
            logger.info("Suburb Saved to db.");
            return suburb.getSuburbId();
        }
        else {
            logger.info("Suburb already present in database");
            return null;
        }
    }
}
