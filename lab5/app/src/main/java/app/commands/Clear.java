package app.commands;
import app.data.Person;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;

public class Clear extends AbstractCommand{

    CollectionHandler collectionHandler;

    public Clear(CollectionHandler collectionHandler) {
        super("clear", "очистить коллекцию");
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

    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            for(Person person :  collectionHandler.getCollection()){
                collectionHandler.removePerson(person);
            }
        }
    }
}