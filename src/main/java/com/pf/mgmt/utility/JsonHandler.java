package com.pf.mgmt.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {
    public String convertObjectToJSON(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json =  mapper.writeValueAsString(o);
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public <T> T parserJSON(String jsonMsg, Class<T> clz) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(jsonMsg, clz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}
