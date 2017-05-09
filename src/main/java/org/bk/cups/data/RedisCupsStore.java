package org.bk.cups.data;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Redis based implementation of a CUPS store
 *
 * @author Biju Kunjummen
 */

@Repository
public class RedisCupsStore implements CupsStore {
    private static final String STORE_KEY = "CUPS_STORE";
    
    private final RedisTemplate<String, Map<String, Object>> redisTemplate;
    
    public RedisCupsStore(RedisTemplate<String, Map<String, Object>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    public Map<String, Object> getCupsParameters(String serviceInstanceId) {
        HashOperations<String, String, Map<String, Object>> hashOperations = getHashOperations();
        return hashOperations.get(STORE_KEY, serviceInstanceId);
    }

    public boolean saveCupsParameters(String serviceInstanceId, Map<String, Object> data) {
        HashOperations<String, String, Map<String, Object>> hashOperations = getHashOperations();
        hashOperations.put(STORE_KEY, serviceInstanceId, data);
        return true;
    }



    public boolean updateCupsParameters(String serviceInstanceId, Map<String, Object> data) {
        HashOperations<String, String, Map<String, Object>> hashOperations = getHashOperations();
        hashOperations.put(STORE_KEY, serviceInstanceId, data);
        return true;
    }

    public boolean deleteCupsParameters(String serviceInstanceId) {
        HashOperations<String, String, Map<String, Object>> hashOperations = getHashOperations();
        hashOperations.delete(STORE_KEY, serviceInstanceId);
        return true;
    }

    public Long getCountOfServiceInstances() {
        HashOperations<String, String, Map<String, Object>> hashOperations = getHashOperations();
        return hashOperations.size(STORE_KEY);
    }

    private HashOperations<String, String, Map<String, Object>> getHashOperations() {
        return this.redisTemplate.opsForHash();
    }
}
