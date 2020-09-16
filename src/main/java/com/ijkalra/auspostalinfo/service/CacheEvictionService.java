package com.ijkalra.auspostalinfo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class CacheEvictionService {

    // evict all entries of postcode cache
    @CacheEvict(value = "postcodeCache", allEntries = true)
    public void evictPostCodeCacheValuesAll() {}

    // evict all entries of suburb cache
    @CacheEvict(value = "suburbsCache", allEntries = true)
    public void evictSuburbsCacheValuesAll() {}

}