package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;
/**
 * Класс отвечающий за команду info
 */
public class Info extends AbstractCommand{

    CollectionHandler collectionHandler;

    public Info(CollectionHandler collectionHandler) {
        super("info", "вывести информацию о коллекции");
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
            IOHandler.println("Кол-во элементов в коллекции: "+collectionHandler.getSize());
            IOHandler.println("Дата инициализации коллекции: "+collectionHandler.getInitDate());
        }
    }
}
