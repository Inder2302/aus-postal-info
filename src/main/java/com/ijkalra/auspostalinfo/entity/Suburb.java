package com.ijkalra.auspostalinfo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "suburb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suburb {

    @Id
    @Column(name = "suburb_uuid")
    private String suburbId;

    @Column(name="post_code")
    private Integer postCode;

    @Column(name = "suburb_name")
    private String suburbName;

    @Column(name = "suburb_state_abbr")
    private String stateAbbr;

    @Transient
    private String state;

    @PrePersist
    private void genId(){
        this.setSuburbId(UUID.randomUUID().toString());
    }
}
