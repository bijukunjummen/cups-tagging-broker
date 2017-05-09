package org.bk.cups.services;

import org.bk.cups.data.CupsStore;
import org.springframework.cloud.servicebroker.model.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Responsible for handling interactions related to creating a CUPS service instance
 *
 * @author Biju Kunjummen
 */

@Service
public class CupsDefiningService implements ServiceInstanceService {
    
    private final CupsStore cupsStore;
    
    public CupsDefiningService(CupsStore cupsStore) {
        this.cupsStore = cupsStore;
    }
    
    @Override
    public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
        Map<String, Object> parameters = request.getParameters();
        this.cupsStore.saveCupsParameters(request.getServiceInstanceId(), parameters);
        CreateServiceInstanceResponse resp = new CreateServiceInstanceResponse()
                .withAsync(false)
                .withInstanceExisted(false);
        return resp;
    }

    @Override
    public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
        return new GetLastServiceOperationResponse()
                .withOperationState(OperationState.SUCCEEDED);
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
        this.cupsStore.deleteCupsParameters(request.getServiceInstanceId());
        return new DeleteServiceInstanceResponse().withAsync(false);
    }

    @Override
    public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
        Map<String, Object> parameters = request.getParameters();
        this.cupsStore.updateCupsParameters(request.getServiceInstanceId(), parameters);
        return new UpdateServiceInstanceResponse().withAsync(false);
    }
    
    public Map<String, Object> getParamsFor(String serviceInstanceId) {
        return this.cupsStore.getCupsParameters(serviceInstanceId);
    }
}
