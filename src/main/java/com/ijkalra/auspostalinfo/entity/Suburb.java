package com.ijkalra.auspostalinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Integer postCode;

    @Column(name = "suburb_name")
    @NotBlank
    private String suburbName;

    @Column(name = "suburb_state_abbr")
    @NotBlank
    private String stateAbbr;

    @Transient
    private String state;

    @PrePersist
    private void genId(){
        // generate a uuid and assign to suburb id before persisting to db
        this.setSuburbId(UUID.randomUUID().toString());
    }
}
