package app.commands;
import app.data.Person;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;

public class RemoveGreater extends AbstractCommand{

    CollectionHandler collectionHandler;

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
            for(Person person :  collectionHandler.getCollection()){
                if(person.getId()>(Integer.parseInt(arg))){
                    collectionHandler.removePerson(person);
                }
            }
        }
    }
}
