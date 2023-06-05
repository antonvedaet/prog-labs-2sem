package ifmo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ifmo.data.Person;

public class Serializator {
    
    public String serializeToJson(CollectionHandler collectionHandler) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return objectMapper.writeValueAsString(collectionHandler.getCollection());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    
}
