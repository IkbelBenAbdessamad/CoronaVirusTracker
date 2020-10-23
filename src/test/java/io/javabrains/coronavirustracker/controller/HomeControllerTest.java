package io.javabrains.coronavirustracker.controller;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HomeController.class)
@Disabled
public class HomeControllerTest {

    @Mock
    private CoronaVirusDataService coronaVirusDataService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validateHomeController() throws Exception {
        List<LocationStats> locationStats = Arrays.asList(
                new LocationStats("US", "New York", 10000, 200)
        );
        when(coronaVirusDataService.getAllStats()).thenReturn(locationStats);


        mockMvc.perform(post("/")).andExpect(status().isOk());


    }
}