package com.example.restapi.controller;

import com.example.restapi.model.Request;
import com.example.restapi.service.FrequencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/frequency")
@RequiredArgsConstructor
public class FrequencyController {

    private final FrequencyService frequencyService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String,Integer>> getFrequency(@RequestBody Request string) {
        return ResponseEntity.ok(frequencyService.getFrequency(string));
    }

}
