package io.javabrains.coronavirustracker.controller;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

class HomeControllerTest {
    @Mock
    private CoronaVirusDataService coronaVirusDataService;

    @InjectMocks
    private HomeController homeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void testList() throws Exception {

        List<LocationStats> locationStats = new ArrayList<>();
        locationStats.add(new LocationStats("US", "New York", 120000, 1000));
        locationStats.add(new LocationStats("", "Tunisia", 12000, 200));



    }
}