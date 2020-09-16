package com.ijkalra.auspostalinfo.entity;

public enum State {
    VIC("Victoria"),
    NSW("New South Wales"),
    ACT("Australian Capital Territory"),
    TAS("Tasmania"),
    NT("Northern Territory"),
    SA("South Australia"),
    WA("Western Australia"),
    QLD("Queensland");

    private String fullName;

    State (String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }
}
