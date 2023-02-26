package app.commands;
import java.util.Collections;

import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;

public class Reorder extends AbstractCommand{

    CollectionHandler collectionHandler;

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
