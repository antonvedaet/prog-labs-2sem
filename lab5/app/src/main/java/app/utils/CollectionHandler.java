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

    public void printPersonList(){
        IOHandler ioHandler  = new IOHandler();
        for (Person person : collection){
            ioHandler.write("id: " + person.getId());
            ioHandler.write("name: " + person.getName());
            ioHandler.write("coordinates: X:" + person.getCoordinates().getX() +" Y:"+ person.getCoordinates().getY());
            ioHandler.write("creation_date: " + person.getCreationDate());
            ioHandler.write("height: " + person.getHeight());
            ioHandler.write("birthday: " + person.getBirthday());
            ioHandler.write("eye_color: " + person.getEyeColor());
            ioHandler.write("hair_color: " + person.getHairColor());
            ioHandler.write("location: " + person.getLocation());
        }
    }
}
