package ifmo.commands;
import ifmo.exceptions.*;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду filter_contains_name
 */
public class FilterContainsName extends AbstractCommand{

    public FilterContainsName() {
        super("filter_contains_name", "выводит элементы, значение поля name которых содержит заданную подстроку");
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(arg.equals("placeholderArg")) throw new ElementAmountException();
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        }
        return false;
    }
}