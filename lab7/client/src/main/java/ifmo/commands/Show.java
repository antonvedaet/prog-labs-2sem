package ifmo.commands;
import ifmo.exceptions.*;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду show
 */
public class Show extends AbstractCommand {
    public Show() {
        super("show", "вывести все элементы коллекции");
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