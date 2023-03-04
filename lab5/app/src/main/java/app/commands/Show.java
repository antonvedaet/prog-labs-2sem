package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.CollectionHandler;
import app.utils.IOHandler;
/**
 * Класс отвечающий за команду show
 */
public class Show extends AbstractCommand {
    CollectionHandler collectionHandler;
    public Show(CollectionHandler collectionHandler) {
        super("show", "вывести все элементы коллекции");
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
            collectionHandler.printPersonList();
        }
    }
}