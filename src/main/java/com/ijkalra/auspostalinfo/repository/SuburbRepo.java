package com.ijkalra.auspostalinfo.repository;

import com.ijkalra.auspostalinfo.entity.Suburb;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuburbRepo extends JpaRepository<Suburb, String> {

    @Cacheable(cacheNames="postcodeCache", key="#postCode", unless="#result.isEmpty()")
    List<Suburb> findByPostCode(int postCode);

    @Cacheable(cacheNames="suburbsCache", key="#suburbName.concat('-').concat(#stateAbbr)", unless="#result == null")
    Suburb findBySuburbNameIgnoreCaseAndStateAbbrIgnoreCase(String suburbName, String stateAbbr);
}
