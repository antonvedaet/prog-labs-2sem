package ifmo.commands;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс отвечающий за команду group_counting_by_id
 */
public class GroupCountingById extends AbstractCommand {

    private CollectionHandler collectionHandler;

    public GroupCountingById(CollectionHandler collectionHandler) {
        super("group_counting_by_id", "сгруппировывает элементы коллекции по значению поля id, выводит количество элементов в каждой группе");
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
            List<Integer> presentIds = new ArrayList<>();
            int counter;
            for(Person person :  collectionHandler.getCollection()){
                presentIds.add(person.getId());
            }
            for(Integer id :  presentIds){
                counter = 0;
                IOHandler.print("Количество элементов с id=" + id + " : ");
                for(Person person :  collectionHandler.getCollection()){
                    if(id.equals(person.getId())){
                        counter++;
                    }
                }
                IOHandler.println(counter);
            }
        }
    }//WIP
}
