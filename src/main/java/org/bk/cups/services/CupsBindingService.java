package org.bk.cups.services;

import org.bk.cups.data.CupsStore;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Responsible for handling interactions at the point an application is bound
 * to a CUPS tagging service instance
 * 
 * @author Biju Kunjummen
 */

@Service
public class CupsBindingService implements ServiceInstanceBindingService {

    private final CupsStore cupsStore;
    
    public CupsBindingService(CupsStore cupsStore) {
        this.cupsStore = cupsStore;
    }
    
    @Override
    public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest request) {
        Map<String, Object> params = this.cupsStore.getCupsParameters(request.getServiceInstanceId());
        CreateServiceInstanceAppBindingResponse response = 
                new CreateServiceInstanceAppBindingResponse()
                    .withCredentials(params);
        return response;
    }

    @Override
    public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {
        //no op..nothing is persisted about an app anyway..
    }
}
