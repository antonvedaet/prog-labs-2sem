package app.utils;
import java.util.LinkedList;
import app.data.Person;

public class CollectionHandler {
    LinkedList<Person> collection;

    
    public CollectionHandler() {
        collection = new LinkedList<>();
    }

    public Boolean addPerson(Person person){
        collection.add(person);
        return true;
    }

    public Boolean removePerson(Person person){
        collection.remove(person);
        return true;
    }
    private int generateNextId(){
        return collection.size() + 1;
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
            IOHandler.println("location: " + person.getLocation());
        }
    }
    //WIP 
}
