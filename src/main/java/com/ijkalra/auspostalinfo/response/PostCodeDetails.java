package com.ijkalra.auspostalinfo.response;

import lombok.Data;

@Data
public class PostCodeDetails {
    private boolean found;
    private int postcode;
    private String suburbName;
}
