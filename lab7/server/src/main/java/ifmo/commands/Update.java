package ifmo.commands;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.DatabaseHandler;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;

/**
 * Класс отвечающий за команду update {id}
 */
public class Update extends AbstractCommand {
    
    private CollectionHandler collectionHandler;
    private DatabaseHandler databaseHandler;

    public Update(CollectionHandler collectionHandler, DatabaseHandler databaseHandler){
        super("update", "обновить значение элемента по id");
        this.collectionHandler = collectionHandler;
        this.databaseHandler = databaseHandler;
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
    public String execute(Request request){
        if(argCheck(request.getArguments())){
            for(Person person :  collectionHandler.getCollection()){
                if(person.getId()==(Integer.parseInt(request.getArguments()))){
                    Person nPerson = request.getPerson();
                    nPerson.setId(Integer.parseInt(request.getArguments()));
                    if(person.getCreator().equals(request.getUser())){
                        collectionHandler.removePerson(person);
                        collectionHandler.addPerson(nPerson);
                    } else {
                        return "Нельзя редактировать элементы созданные другими пользователями";
                    }
                }
            }
            new Save(collectionHandler, databaseHandler).execute(request);
        }
        return "";
    }
}