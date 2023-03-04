package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;
/**
 * Класс отвечающий за команду clear
 */
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
    /**
     * Очистка коллекции
     * @see app.utils.CollectionHandler#clear()
     * @see java.util.Collections
     */
    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            collectionHandler.clear();
        }
    }
}