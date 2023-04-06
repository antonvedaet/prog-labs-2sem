package ifmo.commands;
import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.requests.Request;
import ifmo.utils.IOHandler;
import ifmo.utils.PersonCreator;
import ifmo.utils.CollectionHandler;
import java.time.LocalDate;
/**
 * Класс отвечающий за команду add
 */
public class Add extends AbstractCommand {
    
    private PersonCreator personCreator;
    private CollectionHandler collectionHandler;

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
     * @see ifmo.utils.CollectionHandler
     * @see ifmo.utils.PersonCreator
     */
    @Override
    public void execute(Request request){
        if(argCheck(request.getArguments())){
            Person person = request.getPerson();
            person.setId(collectionHandler.generateNextId());
            collectionHandler.addPerson(person);
        }
    }
}