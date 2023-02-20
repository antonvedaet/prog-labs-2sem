package app;
import app.data.*;
import app.utils.*;
import app.commands.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class App {
    public String getGreeting(){
        return "hello";
    }
    public static void main(String[] args) {
        CollectionHandler cHandler = new CollectionHandler();
        Person p = new Person(cHandler.generateNextId(), "oleg", new Coordinates(0, 0), LocalDate.now(), 182.0f, LocalDateTime.now(), Color.GREEN, Color.BROWN, null);
        cHandler.addPerson(p);
        cHandler.printPersonList();
        AbstractCommand info = new Info(cHandler);
        info.execute("");
    }
    //TODO: CollectionHandler - done
}
