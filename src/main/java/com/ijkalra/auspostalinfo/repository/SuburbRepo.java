package com.ijkalra.auspostalinfo.repository;

import com.ijkalra.auspostalinfo.entity.Suburb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuburbRepo extends JpaRepository<Suburb, String> {
    List<Suburb> findByPostCode(int postCode);
    Suburb findBySuburbNameIgnoreCase(String suburbName);
    Suburb findBySuburbNameIgnoreCaseAndStateAbbrIgnoreCase(String suburbName, String stateAbbr);
}
