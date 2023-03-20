package app.utils;

import java.util.LinkedList;
import app.data.Person;
/**
 * Класс для проверки загружаемых элементов класса Person
 * @see app.data.Person
 */
public class PersonValidator {

    CollectionHandler collectionHandler;
    LinkedList<Person> personsToCheck; 
    public PersonValidator(CollectionHandler collectionHandler) {
        this.collectionHandler = collectionHandler;
        personsToCheck = collectionHandler.getCollection(); 
    }

    Boolean checkPersonValidity(Person person){
        if(person.getId()<=0) return false;
        if(person.getName().trim().isEmpty()) return false;
        if(person.getCoordinates().getX() < -72 || person.getCoordinates().getY() < -647) return false;
        if(person.getHeight()<=0) return false;
        if(person.getBirthday()==null) return false;
        if(person.getEyeColor()==null) return false;
        if(person.getHairColor()==null) return false;
        if(person.getLocation().getName().trim().isEmpty()) return false;
        return true;
    }
    
    public void checkCollectionValidity(){
        LinkedList<Person> personsToDelete = new LinkedList<Person>();
        for (Person person : personsToCheck){
            if(!checkPersonValidity(person)){
                personsToDelete.add(person);
            }
        }
        if(!personsToDelete.isEmpty()){
            IOHandler.println("В файле обнаружены некорректные данные, некорректные элементы коллекции будут удалены.");
            for (Person person: personsToDelete){
                collectionHandler.removePerson(person);
            }
        }
    }
}
