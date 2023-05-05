package ifmo.commands;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;

/**
 * Класс отвечающий за команду update {id}
 */
public class Update extends AbstractCommand {
    
    private CollectionHandler collectionHandler;

    public Update(CollectionHandler collectionHandler){
        super("update", "обновить значение элемента по id");
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
    public void execute(Request request){
        if(argCheck(request.getArguments())){
            for(Person person :  collectionHandler.getCollection()){
                if(person.getId()==(Integer.parseInt(request.getArguments()))){
                    collectionHandler.removePerson(person);
                    Person nPerson = request.getPerson();
                    nPerson.setId(Integer.parseInt(request.getArguments()));
                    collectionHandler.addPerson(nPerson);
                }
            }
        }
    }
}