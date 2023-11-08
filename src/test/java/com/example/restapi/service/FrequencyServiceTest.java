package com.example.restapi.service;

import com.example.restapi.model.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

class FrequencyServiceTest {

    @Mock
    private FrequencyService frequencyService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        frequencyService = new FrequencyService();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void FrequencyService_GetFrequency_ReturnSortedMap() {
        //given
        Request request = new Request("aaabbc");

        Map<String,Integer> rightResult = new HashMap<>();
        rightResult.put("a", 3);
        rightResult.put("b", 2);
        rightResult.put("c", 1);

        //when
        Map<String,Integer> resultFromService = frequencyService.getFrequency(request);

        //then
        Assertions.assertEquals(rightResult, resultFromService);
    }
}