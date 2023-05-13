package ifmo.commands;
import java.sql.SQLException;

import ifmo.exceptions.ElementAmountException;
import ifmo.requests.Request;
import ifmo.utils.IOHandler;
import ifmo.utils.DatabaseHandler;
import ifmo.utils.Hasher;
/**
 * Класс отвечающий за команду register
 */
public class Register extends AbstractCommand {
    
    private DatabaseHandler databaseHandler;

    public Register(DatabaseHandler databaseHandler){
        super("register", "");
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
            Hasher hasher = new Hasher("SHA-256");
            if(!databaseHandler.checkIfUserExists(request.getUser().getLogin(), hasher.encode(request.getUser().getPassword()))){
                try{
                    databaseHandler.register(request.getUser().getLogin(), request.getUser().getPassword());
                } catch (SQLException sqle) {
                    return "Пользователь с таким логином уже существует";
                }
            } else {
                return "Пользователь с таким логином уже существует";
            }
        }
        return "Пользователь успешно зарегестрирован!";
    }
}