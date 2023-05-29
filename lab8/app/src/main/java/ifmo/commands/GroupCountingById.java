package ifmo.commands;
import ifmo.exceptions.*;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду group_counting_by_id
 */
public class GroupCountingById extends AbstractCommand {

    public GroupCountingById() {
        super("group_counting_by_id", "сгруппировывает элементы коллекции по значению поля id, выводит количество элементов в каждой группе");
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
