package ifmo.commands;
import java.sql.SQLException;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.requests.Request;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
import ifmo.utils.DatabaseHandler;
/**
 * Класс отвечающий за команду register
 */
public class Register extends AbstractCommand {
    
    private CollectionHandler collectionHandler;
    private DatabaseHandler databaseHandler;

    public Register(CollectionHandler collectionHandler, DatabaseHandler databaseHandler){
        super("register", "");
        this.collectionHandler = collectionHandler;
        this.databaseHandler = databaseHandler;
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
    /**
     * Создание объекта класса Person и добавление его в коллекцию
     * @see ifmo.utils.CollectionHandler
     * @see ifmo.utils.PersonCreator
     */
    @Override
    public String execute(Request request){
        if(argCheck(request.getArguments())){
            try{
                if(!databaseHandler.checkIfUserExists(request.getUser().getLogin(), request.getUser().getPassword())){
                    databaseHandler.register(request.getUser().getLogin(), request.getUser().getPassword());
                }
            } catch (SQLException sqle){
                return sqle.getMessage();
            }
        }
        return "Пользователь успешно зарегестрирован!";
    }
}