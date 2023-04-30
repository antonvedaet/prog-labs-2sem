package ifmo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ifmo.data.Person;

import java.io.File;
import java.io.IOException;

/**
 * Класс отвечающий за сохранение и загрузку коллекции из файла 
 */
public class FileManager {
    private String filePath;

    public FileManager(){
        this.filePath  = System.getenv("JAVA_VAR");
    }

    public void writeToJson(CollectionHandler collectionHandler) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        File file = new File(filePath);
        objectMapper.writeValue(file, collectionHandler.getCollection());
    }

    public  Person[] readFromFile() throws IOException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            File file = new File(filePath);
            return objectMapper.readValue(file, Person[].class);            
        } catch (NullPointerException npe) {
            IOHandler.println("Переменная окружения JAVA_VAR в которой должен содержаться файл не задана.");
            System.exit(1);
            Person[] pList = new Person[1];
            return pList;
        }
    }
}
