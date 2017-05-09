package org.bk.cups.config;

import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration for a Service Broker Catalog
 * 
 * @author Biju Kunjummen
 */
@Configuration
public class CatalogConfig {

    @Bean
    public Catalog catalog() {
        return new Catalog(Collections.singletonList(
                new ServiceDefinition(
                        "cups-tagging-service",
                        "cups-tagging-service",
                        "CUPS with tagging",
                        true,
                        true,
                        Collections.singletonList(
                                new Plan("default",
                                        "default",
                                        "default",
                                        null)),
                        Arrays.asList("cups-tag", "cups-tag"),
                        getServiceDefinitionMetadata(),
                        null,
                        null)));
    }
    
    private Map<String, Object> getServiceDefinitionMetadata() {
        Map<String, Object> sdMetadata = new HashMap<>();
        sdMetadata.put("displayName", "CUPS tagging Service Broker");
        sdMetadata.put("longDescription", "CUPS tagging Service Broker");
        sdMetadata.put("providerDisplayName", "Pivotal Services");
        sdMetadata.put("documentationUrl", "TBD");
        sdMetadata.put("supportUrl", "TBD");
        return sdMetadata;
    }




}