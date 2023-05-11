package ifmo.commands;
import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.requests.Request;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
/**
 * Класс отвечающий за команду add
 */
public class Add extends AbstractCommand {
    
    private CollectionHandler collectionHandler;

    public Add(CollectionHandler collectionHandler){
        super("add", "добавить новый элемент в коллекцию");
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
    public String execute(Request request){
        if(argCheck(request.getArguments())){
            Person person = request.getPerson();
            person.setId(collectionHandler.generateNextId());
            collectionHandler.addPerson(person);
        }
        return "1";
    }
}