package org.bk.cups.data;

import java.util.Map;

/**
 * Responsible for persisting CUPS for a service to a store
 * 
 * @author Biju Kunjummen
 */
public interface CupsStore {
    Map<String, Object> getCupsParameters(String serviceInstanceId);
    boolean saveCupsParameters(String serviceInstanceId, Map<String, Object> data);
    boolean updateCupsParameters(String serviceInstanceId, Map<String, Object> data);
    boolean deleteCupsParameters(String serviceInstanceId);
    Long getCountOfServiceInstances();
}
