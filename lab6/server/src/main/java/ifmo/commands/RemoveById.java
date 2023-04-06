package ifmo.commands;
import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;
import ifmo.utils.CollectionHandler;
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
    public void execute(Request request){
        if(argCheck(request.getArguments())){
            Person bufferedPerson = null; 
            for(Person person :  collectionHandler.getCollection()){
                if(person.getId()==(Integer.parseInt(request.getArguments()))){
                    bufferedPerson = person;
                }
            }
            collectionHandler.removePerson(bufferedPerson);
        }
    }
}
