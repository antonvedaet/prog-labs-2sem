package ifmo.commands;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
import ifmo.requests.Request;
/**
 * Класс отвечающий за команду info
 */
public class Info extends AbstractCommand{

    private CollectionHandler collectionHandler;

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
    public void execute(Request request){
        if(argCheck(request.getArguments())){
            IOHandler.println("Кол-во элементов в коллекции: "+collectionHandler.getSize());
            IOHandler.println("Дата инициализации коллекции: "+collectionHandler.getInitDate());
        }
    }
}