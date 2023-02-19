package app;
import app.data.*;
import app.utils.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class App {
    public String getGreeting(){
        return "hello";
    }
    public static void main(String[] args) {
        Person p = new Person(0, "oleg", new Coordinates(0, 0), LocalDate.now(), 182.0f, LocalDateTime.now(), Color.GREEN, Color.BROWN, null);
        CollectionHandler cHandler = new CollectionHandler();
        cHandler.addPerson(p);
        cHandler.printPersonList();
    }
    //TODO: CollectionHandler - done

}
