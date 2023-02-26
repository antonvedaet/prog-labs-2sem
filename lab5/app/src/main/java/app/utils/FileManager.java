package app.utils;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import app.data.Person;


public class FileManager {

    public FileManager(){
    }

    public void writeToJson(CollectionHandler collectionHandler) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        File file = new File("collection.json");
        objectMapper.writeValue(file, collectionHandler.getCollection());
    }

    public  Person[] readFromFile(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("collection.json");
        return objectMapper.readValue(file, Person[].class);
    }
}
