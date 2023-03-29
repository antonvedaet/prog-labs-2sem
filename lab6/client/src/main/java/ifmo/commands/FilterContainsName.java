package ifmo.commands;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду filter_contains_name
 */
public class FilterContainsName extends AbstractCommand{

    private CollectionHandler collectionHandler;

    public FilterContainsName(CollectionHandler collectionHandler) {
        super("filter_contains_name", "выводит элементы, значение поля name которых содержит заданную подстроку");
        this.collectionHandler = collectionHandler;
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

    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            for(Person person :  collectionHandler.getCollection()){
                if(person.getName().contains(arg)){
                    collectionHandler.printPerson(person);
                }
            }
        }
    }
}