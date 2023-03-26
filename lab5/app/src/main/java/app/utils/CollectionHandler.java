package app.utils;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Collections;
import app.data.Person;
/**
 * Класс отвечающий за управление коллекцией
 */
public class CollectionHandler {
    private LinkedList<Person> collection;
    private LocalDate initDate;
    
    public CollectionHandler() {
        collection = new LinkedList<>();
        initDate = LocalDate.now();
    }
    /**
     * Добавляет объект класса Person в коллекцию
     * @param person : Объект класса Person
     * @see app.data.Person
     * @return
     */
    public Boolean addPerson(Person person){
        collection.add(person);
        return true;
    }
    /**
     * Удаляет объект класса Person из коллекции
     * @param person : Объект класса Person
     * @see app.data.Person
     * @return
     */
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
        int nextId = 1;
        for(Person person :  collection){
            if(person.getId()>=nextId){
                nextId = person.getId();
            }
        }
        return nextId+1;
    }

    public void reorder(){
        Collections.reverse(collection);
    }
    /**
     * Загружает коллецию из json
     * @see app.utils.FileManager#readFromFile()
     */
    public void loadCollection(){
        FileManager fileManager = new FileManager();
        Person[] persons;
        try{
           persons = fileManager.readFromFile();
           for (Person person : persons) {
            addPerson(person);
        }
        } catch (IOException e) {
            IOHandler.println("Ошибка при чтении файла: " + e.getMessage());
        }
        
    }
    /**
     *Выводит все объекты в коллекции в строковом представлении
     */
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
    /**
     *Выводит объект из коллекции в строковом представлении
     *@param person: Объект класса Person
     *@see app.data.Person
     */
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
}
