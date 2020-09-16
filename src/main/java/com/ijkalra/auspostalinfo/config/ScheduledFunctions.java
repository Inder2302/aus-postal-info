package com.ijkalra.auspostalinfo.config;

import com.ijkalra.auspostalinfo.service.CacheEvictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledFunctions {

    @Autowired
    private CacheEvictionService cacheEvictionService;

    private Logger logger = LoggerFactory.getLogger(ScheduledFunctions.class);

    // clears cache on a scheduled basis so that we get updated data from DB
    // @Scheduled(fixedDelay = 300000)
    public void clearBookCache(){
        logger.info("Running scheduled job to clear Cached values");
        cacheEvictionService.evictPostCodeCacheValuesAll();
        cacheEvictionService.evictSuburbsCacheValuesAll();
    }
}
