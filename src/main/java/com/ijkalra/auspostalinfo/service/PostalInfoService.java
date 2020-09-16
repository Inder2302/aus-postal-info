package com.ijkalra.auspostalinfo.service;

import com.ijkalra.auspostalinfo.entity.State;
import com.ijkalra.auspostalinfo.entity.Suburb;
import com.ijkalra.auspostalinfo.repository.SuburbRepo;
import com.ijkalra.auspostalinfo.response.PostCodeDetails;
import com.ijkalra.auspostalinfo.response.SuburbDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostalInfoService {

    @Autowired
    private SuburbRepo suburbRepo;

    public SuburbDetails getSuburbsByPostCode(int postcode) {
        // Create response type object
        SuburbDetails suburbDetails = new SuburbDetails();

        //look up in database via repository function
        List<Suburb> suburbsByPostCode = suburbRepo.findByPostCode(postcode);

        // loop in the list of suburbs found in db and set the full name of state
        // as derived from State Enu,
        suburbsByPostCode.forEach(s -> s.setState(State.valueOf(s.getStateAbbr()).getFullName()));

        // set Result list
        suburbDetails.setResult(suburbsByPostCode);

        //set boolean flag
        suburbDetails.setFound(!suburbDetails.getResult().isEmpty());

        // return response object
        return  suburbDetails;
    }

    public PostCodeDetails getPostCodeBySuburbName(String suburbName) {
        // create response object
        PostCodeDetails postCodeDetails = new PostCodeDetails();

        //lookup data in db
        Suburb suburb = suburbRepo.findBySuburbNameIgnoreCase(suburbName);

        //fill the response object with data from db

        // found = true -> if suburb !=null
        if (suburb == null){
            postCodeDetails.setFound(false);
            postCodeDetails.setPostcode(-1);
            postCodeDetails.setSuburbName("NOT_FOUND");
        }
        else {
            postCodeDetails.setFound(true);
            postCodeDetails.setPostcode(suburb.getPostCode());
            postCodeDetails.setSuburbName(suburb.getSuburbName());
        }
        return postCodeDetails;
    }

    public void persist(Suburb suburb) {
        if (suburbRepo.findBySuburbNameIgnoreCaseAndStateAbbrIgnoreCase(suburb.getSuburbName(), suburb.getStateAbbr()) == null){
            suburb.setStateAbbr(suburb.getStateAbbr().toUpperCase());
            suburbRepo.save(suburb);
        }
        else {
            System.out.println("Throw exception here");
        }
    }
}
