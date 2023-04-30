package ifmo.commands;
import ifmo.exceptions.*;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду shuffle
 */
public class Shuffle extends AbstractCommand{

    public Shuffle() {
        super("shuffle", "перемешать коллекцию в случайном порядке");
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
}