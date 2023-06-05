package ifmo.utils;

import java.io.IOException;
import java.util.LinkedList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ifmo.data.Person;

public class Deserializator {
    
public LinkedList<Person> deserializeFromJson(String json){
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    TypeReference<LinkedList<Person>> typeRef = new TypeReference<LinkedList<Person>>() {};
    try {
        return objectMapper.readValue(json, typeRef);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
        return null;
    }
}
}
