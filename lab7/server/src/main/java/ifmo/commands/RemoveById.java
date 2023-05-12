package ifmo.commands;
import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;
import ifmo.utils.CollectionHandler;
import ifmo.utils.DatabaseHandler;

import java.util.Optional;
/**
 * Класс отвечающий за команду remove_by_id {id}
 */
public class RemoveById extends AbstractCommand{

    private CollectionHandler collectionHandler;
    private DatabaseHandler databaseHandler;

    public RemoveById(CollectionHandler collectionHandler, DatabaseHandler databaseHandler) {
        super("remove_by_id", "удалить элемент коллекции по id");
        this.collectionHandler = collectionHandler;
        this.databaseHandler = databaseHandler;
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
            if(bufferedPerson.get().getCreator().equals(request.getUser())){
            bufferedPerson.ifPresent(collectionHandler::removePerson);
            databaseHandler.deletePerson(bufferedPerson.get().getId());
            } else {
                return "Нельзя редактировать элементы созданные другими пользователями";
            }
        }
        return "";
    }
}
