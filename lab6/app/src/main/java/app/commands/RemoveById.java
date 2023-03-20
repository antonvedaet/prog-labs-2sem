package app.commands;
import app.data.Person;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;
/**
 * Класс отвечающий за команду remove_by_id {id}
 */
public class RemoveById extends AbstractCommand{

    CollectionHandler collectionHandler;

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
    public void execute(String arg){
        if(argCheck(arg)){
            Person bufferedPerson = null; 
            for(Person person :  collectionHandler.getCollection()){
                if(person.getId()==(Integer.parseInt(arg))){
                    bufferedPerson = person;
                }
            }
            collectionHandler.removePerson(bufferedPerson);
        }
    }
}
