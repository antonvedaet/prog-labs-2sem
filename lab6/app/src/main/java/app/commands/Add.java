package app.commands;
import app.data.Person;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.PersonCreator;
import app.utils.CollectionHandler;
import java.time.LocalDate;
/**
 * Класс отвечающий за команду add
 */
public class Add extends AbstractCommand {
    
    PersonCreator personCreator;
    CollectionHandler collectionHandler;

    public Add(PersonCreator personCreator, CollectionHandler collectionHandler){
        super("add", "добавить новый элемент в коллекцию");
        this.personCreator = personCreator;
        this.collectionHandler = collectionHandler;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(!arg.equals("placeholderArg")) throw new ElementAmountException();
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        } 
        return false;
    }
    /**
     * Создание объекта класса Person и добавление его в коллекцию
     * @see app.utils.CollectionHandler
     * @see app.utils.PersonCreator
     */
    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            collectionHandler.addPerson(new Person(collectionHandler.generateNextId(), personCreator.nameCreate(), personCreator.coordinatesCreate(), LocalDate.now(), personCreator.heightCreate(), personCreator.bdayCreate(), personCreator.eyeColorChoose(), personCreator.hairColorChoose(), personCreator.locationCreate()));
        }
    }
}