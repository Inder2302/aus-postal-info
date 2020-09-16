package com.ijkalra.auspostalinfo.controller;

import com.ijkalra.auspostalinfo.response.SuburbDetails;
import com.ijkalra.auspostalinfo.service.PostalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postcode")
public class PostcodeController {

    @Autowired
    private PostalInfoService postalInfoService;

    @GetMapping("/{postcode}")
    public SuburbDetails getPostCodeInfo(@PathVariable("postcode") int postcode) {
        return postalInfoService.getSuburbsByPostCode(postcode);
    }

}
