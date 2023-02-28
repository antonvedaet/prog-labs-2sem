package app.utils;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Collections;
import app.data.Person;

public class CollectionHandler {
    LinkedList<Person> collection;
    private LocalDate initDate;
    private int idCounter;
    
    public CollectionHandler() {
        collection = new LinkedList<>();
        initDate = LocalDate.now();
        idCounter = 0;
    }

    public Boolean addPerson(Person person){
        collection.add(person);
        return true;
    }

    public Boolean removePerson(Person person){
        collection.remove(person);
        return true;
    }

    public Person getPerson(Person person){
        return person;
    }

    public int getSize(){
        return collection.size();
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public LinkedList<Person> getCollection(){
        return collection;
    }

    public void clear(){
        collection.clear();
    }

    public void shuffle(){
        Collections.shuffle(collection);
    }

    public int generateNextId(){
        return  idCounter++;
    }

    public void reorder(){
        Collections.reverse(collection);
    }

    public void loadCollection(){
        FileManager fileManager = new FileManager();
        Person[] persons;
        try{
           persons = fileManager.readFromFile();
           for (Person person : persons) {
            addPerson(person);
            generateNextId();
        }
        } catch (IOException e) {
            IOHandler.println("Ошибка чтения файла");
        }
        
    }

    public void printPersonList(){
        for (Person person : collection){
            IOHandler.println("id: " + person.getId());
            IOHandler.println("name: " + person.getName());
            IOHandler.println("coordinates: X:" + person.getCoordinates().getX() +" Y:"+ person.getCoordinates().getY());
            IOHandler.println("creation_date: " + person.getCreationDate());
            IOHandler.println("height: " + person.getHeight());
            IOHandler.println("birthday: " + person.getBirthday());
            IOHandler.println("eye_color: " + person.getEyeColor());
            IOHandler.println("hair_color: " + person.getHairColor());
            IOHandler.println("location: X:" + person.getLocation().getX() + " Y:"+person.getLocation().getY()+ " Z:"+person.getLocation().getZ() + " name:" + person.getLocation().getName());
            IOHandler.println("------------------------------------------");
        }
    }

    public void printPerson(Person person){
        IOHandler.println("id: " + person.getId());
        IOHandler.println("name: " + person.getName());
        IOHandler.println("coordinates: X:" + person.getCoordinates().getX() +" Y:"+ person.getCoordinates().getY());
        IOHandler.println("creation_date: " + person.getCreationDate());
        IOHandler.println("height: " + person.getHeight());
        IOHandler.println("birthday: " + person.getBirthday());
        IOHandler.println("eye_color: " + person.getEyeColor());
        IOHandler.println("hair_color: " + person.getHairColor());
        IOHandler.println("location: X:" + person.getLocation().getX() + " Y:"+person.getLocation().getY()+ " Z:"+person.getLocation().getZ() + " name:" + person.getLocation().getName());
        IOHandler.println("------------------------------------------");
    }
    //WIP 
}
