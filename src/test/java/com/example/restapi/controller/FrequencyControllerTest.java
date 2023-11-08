package com.example.restapi.controller;

import com.example.restapi.model.Request;
import com.example.restapi.service.FrequencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FrequencyController.class)
@AutoConfigureMockMvc
class FrequencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FrequencyService frequencyService;
    private AutoCloseable autoCloseable;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void FrequencyController_GetFrequency_ReturnOk() throws Exception {
        Request request = new Request("aaabbc");

        Map<String,Integer> rightResult = new HashMap<>();
        rightResult.put("a", 3);
        rightResult.put("b", 2);
        rightResult.put("c", 1);

        when(frequencyService.getFrequency(any(Request.class))).thenReturn(rightResult);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/frequency")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                //check the status
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //check the content
                .andExpect(jsonPath("$.a").value(3))
                .andExpect(jsonPath("$.b").value(2))
                .andExpect(jsonPath("$.c").value(1));


    }
}