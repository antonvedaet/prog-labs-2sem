package ifmo.commands;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;

import java.util.LinkedList;
import java.util.stream.Collectors;
/**
 * Класс отвечающий за команду remove_greater {id value}
 */
public class RemoveGreater extends AbstractCommand{

    private CollectionHandler collectionHandler;

    public RemoveGreater(CollectionHandler collectionHandler) {
        super("remove_greater", " удаляет из коллекции все элементы, превышающие заданный");
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
            LinkedList<Person> filteredList = collectionHandler.getCollection()
        .stream()
        .filter(person -> person.getId() <= Integer.parseInt(request.getArguments()))
        .collect(Collectors.toCollection(LinkedList::new));

        collectionHandler.clear();

        collectionHandler.setCollection(filteredList);

        }
    return "1";
    }
}
