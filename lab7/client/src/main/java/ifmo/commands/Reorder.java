package ifmo.commands;
import ifmo.exceptions.*;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду reorder
 */
public class Reorder extends AbstractCommand{

    public Reorder() {
        super("reorder", "отсортировать коллекцию в обратном порядке");
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
