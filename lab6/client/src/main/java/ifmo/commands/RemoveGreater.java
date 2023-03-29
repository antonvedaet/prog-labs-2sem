package ifmo.commands;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;

import java.util.ArrayList;
import java.util.List;
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
    public void execute(String arg){
        if(argCheck(arg)){
            List<Person> bufferedPersons = new ArrayList<Person>();
            for(Person person :  collectionHandler.getCollection()){
                if(person.getId()>(Integer.parseInt(arg))){
                    bufferedPersons.add(person);
                }
            }
            for(Person person: bufferedPersons){
                collectionHandler.removePerson(person);
            }
        }
    }
}
