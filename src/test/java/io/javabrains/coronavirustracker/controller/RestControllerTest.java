package io.javabrains.coronavirustracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.coronavirustracker.models.LocationStats;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RestController.class)
class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Verifying Input Serialization")
    void whenValidInput_thenReturn200() throws Exception {

        LocationStats locationStats = new LocationStats("US", "New York", 10000, 200);

        mockMvc.perform(post("/LocationStats/{LocationStatsId}/add", "HelloWorld")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(locationStats)))
                .andExpect(status().isOk()); //ok status is  equivalent to 200 server response
    }


    @Test
    @DisplayName("Verifying Input Validation")
    void whenNUllValue_thenReturn400() throws Exception {
        LocationStats locationStats = new LocationStats(null, null, 10000, 200);


        mockMvc.perform(post("/LocationStats/{LocationStatsId}/add", "HelloWorld2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(locationStats)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Verifying Output Serialization")
    void whenValidInput_thenReturnsUserResource() throws Exception {
        LocationStats locationStats = new LocationStats("US ", "NY", 10000, 200);

        MvcResult mvcResult = mockMvc.perform(post("/LocationStats/{LocationStatsId}/add", "HelloWorld3")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(locationStats)))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(locationStats));
    }

}