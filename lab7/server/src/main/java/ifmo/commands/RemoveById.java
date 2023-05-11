package ifmo.commands;
import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;
import ifmo.utils.CollectionHandler;

import java.util.Optional;
/**
 * Класс отвечающий за команду remove_by_id {id}
 */
public class RemoveById extends AbstractCommand{

    private CollectionHandler collectionHandler;

    public RemoveById(CollectionHandler collectionHandler) {
        super("remove_by_id", "удалить элемент коллекции по id");
        this.collectionHandler = collectionHandler;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(arg.equals("placeholderArg")) throw new ElementAmountException();
            Integer.parseInt(arg);
            
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        } catch (NumberFormatException ex) {
            IOHandler.println("Введите число");
        }
        return false;
    }

    @Override
    public String execute(Request request){
        if(argCheck(request.getArguments())){
            Optional<Person> bufferedPerson = collectionHandler.getCollection().stream()
            .filter(person -> person.getId() == Integer.parseInt(request.getArguments()))
            .findFirst();

            bufferedPerson.ifPresent(collectionHandler::removePerson);
        }
        return "1";
    }
}
