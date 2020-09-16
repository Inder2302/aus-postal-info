package com.ijkalra.auspostalinfo.service;

import org.springframework.cache.annotation.CacheEvict;

public class CacheEvictionService {

    // Todo: These services can be called to clear out cached values on a Scheduled basis

    // evict all entries of postcode cache
    @CacheEvict(value = "postcodeCache", allEntries = true)
    public void evictPostCodeCacheValuesAll() {}

    // evict all entries of suburb cache
    @CacheEvict(value = "suburbsCache", allEntries = true)
    public void evictSuburbsCacheValuesAll() {}

}