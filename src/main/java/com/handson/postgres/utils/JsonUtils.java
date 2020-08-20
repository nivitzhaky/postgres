package com.handson.postgres.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonUtils {
    public static <T> List<T> makeList(List<Map<String, Object>> source, Class<T> targetClazz, ObjectMapper om) {
        return source.stream().map(x -> {
            try {
                return om.readValue(om.writeValueAsString(x), targetClazz);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Problem reading results for:" + source.toString(), e);
            }
        }).collect(Collectors.toList());
    }

    public static <T> T makeItem(Map<String, Object> source, Class<T> targetClazz, ObjectMapper om) {
        try {
            return om.readValue(om.writeValueAsString(source), targetClazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Problem reading results for:" + source.toString(), e);
        }

    }

}
