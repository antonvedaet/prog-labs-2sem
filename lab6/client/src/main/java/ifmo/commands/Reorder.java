package ifmo.commands;

import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду reorder
 */
public class Reorder extends AbstractCommand{

    private CollectionHandler collectionHandler;

    public Reorder(CollectionHandler collectionHandler) {
        super("reorder", "отсортировать коллекцию в обратном порядке");
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
            collectionHandler.reorder();
        }
    }
}
