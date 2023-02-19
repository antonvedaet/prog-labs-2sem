package app.utils;
import java.util.LinkedList;
import app.data.Person;

public class CollectionHandler {
    LinkedList<Person> collection;

    
    public CollectionHandler() {
        collection = new LinkedList<>();
    }

    public Boolean addPerson(Person p){
        collection.add(p);
        return true;
    }

    public Boolean removePerson(Person p){
        collection.remove(p);
        return true;
    }

    public void printPersonList(){
        IOHandler n  = new IOHandler();
        for (Person p : collection){
            n.write("id: " + p.getId());
            n.write("name: " + p.getName());
            n.write("coordinates: " + p.getCoordinates());
            n.write("creation_date: " + p.getCreationDate());
            n.write("height: " + p.getHeight());
            n.write("birthday: " + p.getBirthday());
            n.write("eye_color: " + p.getEyeColor());
            n.write("hair_color: " + p.getHairColor());
            n.write("location: " + p.getLocation());
        }
    }
}
