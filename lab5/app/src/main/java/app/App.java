package app;
import app.data.*;
import app.utils.*;
import app.commands.*;
import java.time.LocalDate;

public class App {
    public String getGreeting(){
        return "hello";
    }
    public static void main(String[] args) {
        PersonCreator pc = new PersonCreator();
        CollectionHandler cHandler = new CollectionHandler();
        Person p = new Person(cHandler.generateNextId(), pc.nameCreate(), pc.coordinatesCreate(),
         LocalDate.now(),pc.heightCreate(), pc.bdayCreate(), Color.GREEN, Color.BROWN, pc.locationCreate());
        cHandler.addPerson(p);
        cHandler.printPersonList();
        AbstractCommand info = new Info(cHandler);
        info.execute("");
    }
}