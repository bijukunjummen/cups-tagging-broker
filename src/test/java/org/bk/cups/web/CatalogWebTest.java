package org.bk.cups.web;

import org.bk.cups.config.CatalogConfig;
import org.bk.cups.data.CupsStore;
import org.bk.cups.services.CupsBindingService;
import org.bk.cups.services.CupsDefiningService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.servicebroker.config.ServiceBrokerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CatalogWebTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testForCatalog() throws Exception {
        this.mockMvc.perform(get("/v2/catalog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.services[0].id").value("cups-tagging-service"))
                .andExpect(jsonPath("$.services[0].description").value("CUPS with tagging"))
                .andExpect(jsonPath("$.services[0].plans[0].id").value("default"))
                .andExpect(jsonPath("$.services[0].plans[0].name").value("default"));
    }
    
    @Import({CatalogConfig.class})
    @Configuration
    @ImportAutoConfiguration(classes = ServiceBrokerAutoConfiguration.class)
    public static class WebConfig {
        
        @Bean
        public CupsBindingService cupsBindingService() {
            return new CupsBindingService(cupsStore());
        }
        
        @Bean
        public CupsDefiningService cupsDefiningService() {
            return new CupsDefiningService(cupsStore());
        }
        
        @Bean
        public CupsStore cupsStore() {
            return mock(CupsStore.class);
        }
    }
}
