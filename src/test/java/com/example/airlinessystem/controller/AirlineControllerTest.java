package com.example.airlinessystem.controller;

import com.example.airlinessystem.model.Airline;
import com.example.airlinessystem.service.AirlineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AirlineController.class)
class AirlineControllerTest {

    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean //Controller test yazarken kullanılır, service test yazarken @InjectMock
    private AirlineService airlineService;

    @Test
    void addAirline() throws Exception {
    //Given
    final Airline airline = new Airline();
    airline.setId(1L);
    airline.setCode("TestCode1");

    //When
    ResultActions resultActions = mockMvc.perform(post("/api/airlines")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(airline)));

    //Then
        ArgumentCaptor<Airline> captor = ArgumentCaptor.forClass(Airline.class);
        Mockito.verify(airlineService, Mockito.times(1))
                .addAirline(captor.capture());
        Assertions.assertThat(captor.getValue().getId()).isEqualTo(1L);
        Assertions.assertThat(captor.getValue().getCode()).isEqualTo("TestCode1");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllAirlines() throws Exception {

        //Given
        final Airline airline = new Airline();
        airline.setId(1L);
        airline.setCode("TestCode1");

        //When
        Mockito.when(airlineService.getAllAirlines())
                .thenReturn(Arrays.asList(airline));

        MvcResult mvcResult = mockMvc.perform(
                get("/api/airlines").accept(
                        CONTENT_TYPE
                )).andReturn();
        // Then

        String responseBody = mvcResult.getResponse().getContentAsString();
        Mockito.verify(airlineService, Mockito.times(1))
                .getAllAirlines();

        Assertions.assertThat(objectMapper.writeValueAsString(
                Arrays.asList(airline)
        )).isEqualToIgnoringWhitespace(responseBody);

    }

    @Test
    void searchAirlines() {
    }
}