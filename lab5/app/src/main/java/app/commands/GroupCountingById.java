package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;
import java.util.ArrayList;
import java.util.List;

import app.data.Person;
/**
 * Класс отвечающий за команду group_counting_by_id
 */
public class GroupCountingById extends AbstractCommand {

    CollectionHandler collectionHandler;

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
            List<Integer> presentIds = new ArrayList<Integer>();
            int counter = 0;
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
