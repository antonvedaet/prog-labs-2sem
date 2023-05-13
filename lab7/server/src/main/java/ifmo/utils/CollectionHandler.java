package ifmo.utils;

import ifmo.data.Person;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс отвечающий за управление коллекцией
 */
public class CollectionHandler {
    private LinkedList<Person> collection;
    private LocalDate initDate;
    private ReadWriteLock lock;
    public CollectionHandler() {
        collection = new LinkedList<>();
        initDate = LocalDate.now();
        lock = new ReentrantReadWriteLock();
    }
    /**
     * Добавляет объект класса Person в коллекцию
     * @param person : Объект класса Person
     * @see ifmo.data.Person
     * @return
     */
    public Boolean addPerson(Person person){
        lock.writeLock().lock();
        try {
            collection.add(person);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }
    /**
     * Удаляет объект класса Person из коллекции
     * @param person : Объект класса Person
     * @see ifmo.data.Person
     * @return
     */
    public Boolean removePerson(Person person){
        lock.writeLock().lock();
        try {
            collection.remove(person);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Person getPerson(int id){
        lock.readLock().lock();
        try{
            for(Person person :  collection){
                if(person.getId()==id){
                    return person;
                }
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }

    }

    public void setCollection(LinkedList<Person> pc){
        lock.writeLock().lock();
        try{
            this.collection = pc;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getSize(){
        lock.readLock().lock();
        try {
            return collection.size();
        } finally {
            lock.readLock().unlock();
        }
    
    }

    public LocalDate getInitDate() {
        lock.readLock().lock();
        try {
            return initDate;
        } finally {
            lock.readLock().unlock();
        }
    }

    public LinkedList<Person> getCollection(){
        lock.readLock().lock();
        try {
            return collection;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void clear(){
        lock.readLock().lock();
        try{
            collection = collection.stream()
            .limit(0)
            .collect(Collectors.toCollection(LinkedList::new));
        }finally{
            lock.readLock().unlock();
        }
    }

    public void shuffle(){
        lock.readLock().lock();
        try{
            Collections.shuffle(collection);
        }finally{
            lock.readLock().unlock();
        }
         //яollections.shuffle(collection); не знаю как сделать это со stream api
    }

    public int generateNextId(){
        lock.readLock().lock();
        try{
            int nextId = 0;
            for(Person person :  collection){
                if(person.getId()>=nextId){
                    nextId = person.getId();
                }
            }
            return nextId+1;
        }finally{
            lock.readLock().unlock();
        }
    }

    public void reorder(){
        lock.readLock().lock();
        try{
            collection  = Stream.iterate(collection.size() - 1, i -> i - 1)
            .limit(collection.size())
            .map(collection::get)
            .collect(Collectors.toCollection(LinkedList::new));
        }finally{
            lock.readLock().unlock();
        }
    }

    /**
     *Выводит все объекты в коллекции в строковом представлении
     */
    public void printPersonList(PrintWriter output){
        lock.readLock().lock();
        try{
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
        } finally {
            lock.readLock().unlock();
        }
    }
    /**
     *Выводит объект из коллекции в строковом представлении
     *@param person: Объект класса Person
     *@see ifmo.data.Person
     */
    public void printPerson(Person person, PrintWriter output){
        lock.readLock().lock();
        try{
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
        
        } finally {
            lock.readLock().unlock();
        }
    }
}
