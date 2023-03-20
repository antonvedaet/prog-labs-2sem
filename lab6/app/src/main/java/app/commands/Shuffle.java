package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;
/**
 * Класс отвечающий за команду shuffle
 */
public class Shuffle extends AbstractCommand{

    CollectionHandler collectionHandler;

    public Shuffle(CollectionHandler collectionHandler) {
        super("shuffle", "перемешать коллекцию в случайном порядке");
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
            collectionHandler.shuffle();
        }
    }
}