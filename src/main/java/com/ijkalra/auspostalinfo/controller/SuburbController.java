package com.ijkalra.auspostalinfo.controller;

import com.ijkalra.auspostalinfo.entity.Suburb;
import com.ijkalra.auspostalinfo.response.PostCodeDetails;
import com.ijkalra.auspostalinfo.service.PostalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/suburb")
@RestController
public class SuburbController {

    @Autowired
    private PostalInfoService postalInfoService;

    @GetMapping("/{suburbname}")
    public PostCodeDetails getPostCodeBySuburbName(@PathVariable("suburbname") String suburbname){
        return postalInfoService.getPostCodeBySuburbName(suburbname);
    }

    @PostMapping("/newsuburb")
    public void createNewSuburbProfile(@RequestBody Suburb suburb) {
        postalInfoService.persist(suburb);
    }
}
