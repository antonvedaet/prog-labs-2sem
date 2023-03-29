package ifmo.commands;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду count_less_than_height
 */
public class CountLessThanHeight extends AbstractCommand{

    private CollectionHandler collectionHandler;

    public CountLessThanHeight(CollectionHandler collectionHandler) {
        super("count_less_than_height", "выводит количество элементов, значение поля height которых меньше заданного");
        this.collectionHandler = collectionHandler;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(arg.equals("placeholderArg")) throw new ElementAmountException();
            Integer.parseInt(arg);
            
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        } catch (NumberFormatException ex) {
            IOHandler.println("Введите число");
        }
        return false;
    }

    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            int count = 0;
            for(Person person :  collectionHandler.getCollection()){
                if(person.getHeight()<Integer.parseInt(arg)){
                    count++;
                }
            }
            IOHandler.println("Количество элементов с значением height меньше введенного: "+count);
        }
    }
}