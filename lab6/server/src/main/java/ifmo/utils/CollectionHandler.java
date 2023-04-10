package ifmo.utils;

import ifmo.data.Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collections;
import java.util.LinkedList;

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
     * @see ifmo.data.Person
     * @return
     */
    public Boolean addPerson(Person person){
        collection.add(person);
        return true;
    }
    /**
     * Удаляет объект класса Person из коллекции
     * @param person : Объект класса Person
     * @see ifmo.data.Person
     * @return
     */
    public Boolean removePerson(Person person){
        collection.remove(person);
        return true;
    }

    public Person getPerson(Person person){
        return person;
    }

    public void setCollection(LinkedList<Person> pc){
        this.collection = pc;
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
        collection = collection.stream()
           .limit(0)
           .collect(Collectors.toCollection(LinkedList::new));

    }

    public void shuffle(){
        Collections.shuffle(collection); //яollections.shuffle(collection); не знаю как сделать это со stream api
    }

    public int generateNextId(){
        int nextId = 0;
        for(Person person :  collection){
            if(person.getId()>=nextId){
                nextId = person.getId();
            }
        }
        return nextId+1;
    }

    public void reorder(){
        collection  = Stream.iterate(collection.size() - 1, i -> i - 1)
        .limit(collection.size())
        .map(collection::get)
        .collect(Collectors.toCollection(LinkedList::new));
    }
    /**
     * Загружает коллецию из json
     * @see ifmo.utils.FileManager#readFromFile()
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
            IOHandler.println("Ошибка при чтении файла: " + e);
        }
        
    }
    /**
     *Выводит все объекты в коллекции в строковом представлении
     */
    public void printPersonList(PrintWriter output){
        for (Person person : collection){
            output.println("id: " + person.getId());
            output.println("name: " + person.getName());
            output.println("coordinates: X:" + person.getCoordinates().getX() +" Y:"+ person.getCoordinates().getY());
            output.println("creation_date: " + person.getCreationDate());
            output.println("height: " + person.getHeight());
            output.println("birthday: " + person.getBirthday());
            output.println("eye_color: " + person.getEyeColor());
            output.println("hair_color: " + person.getHairColor());
            output.println("location: X:" + person.getLocation().getX() + " Y:"+person.getLocation().getY()+ " Z:"+person.getLocation().getZ() + " name:" + person.getLocation().getName());
            output.println("------------------------------------------");
        }
    }
    /**
     *Выводит объект из коллекции в строковом представлении
     *@param person: Объект класса Person
     *@see ifmo.data.Person
     */
    public void printPerson(Person person, PrintWriter output){
        output.println("id: " + person.getId());
        output.println("name: " + person.getName());
        output.println("coordinates: X:" + person.getCoordinates().getX() +" Y:"+ person.getCoordinates().getY());
        output.println("creation_date: " + person.getCreationDate());
        output.println("height: " + person.getHeight());
        output.println("birthday: " + person.getBirthday());
        output.println("eye_color: " + person.getEyeColor());
        output.println("hair_color: " + person.getHairColor());
        output.println("location: X:" + person.getLocation().getX() + " Y:"+person.getLocation().getY()+ " Z:"+person.getLocation().getZ() + " name:" + person.getLocation().getName());
        output.println("------------------------------------------");
    }
}
