package com.example.restapi.service;

import com.example.restapi.model.Request;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FrequencyService {

    public Map<String, Integer> getFrequency(Request string) {
        Map<Character, Integer> countMap = new HashMap<>();

        for (Character c: string.getValue().toCharArray()) {
            int count = countMap.getOrDefault(c, 0);
            countMap.put(c, count + 1);
        }

        return countMap.entrySet()
                .stream()
                .sorted((Map.Entry.<Character,Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap((e -> String.valueOf(e.getKey())), Map.Entry::getValue, (v1,v2) -> v1, LinkedHashMap::new));
    }
}
