package ifmo.commands;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;
/**
 * Класс отвечающий за команду show
 */
public class Show extends AbstractCommand {
    private CollectionHandler collectionHandler;
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
    public void execute(Request request){
        if(argCheck(request.getArguments())){
            collectionHandler.printPersonList();
        }
    }
}