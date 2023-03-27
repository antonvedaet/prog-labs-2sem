package ifmo.commands;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
/**
 * Класс отвечающий за команду shuffle
 */
public class Shuffle extends AbstractCommand{

    private CollectionHandler collectionHandler;

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