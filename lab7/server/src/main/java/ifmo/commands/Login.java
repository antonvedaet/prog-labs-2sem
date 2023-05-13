package ifmo.commands;
import ifmo.exceptions.ElementAmountException;
import ifmo.requests.Request;
import ifmo.utils.IOHandler;
import ifmo.utils.DatabaseHandler;
import ifmo.utils.Hasher;
/**
 * Класс отвечающий за команду register
 */
public class Login extends AbstractCommand {
    
    private DatabaseHandler databaseHandler;

    public Login(DatabaseHandler databaseHandler){
        super("login", "");
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
                return "Неправильный логин или пароль";
            } else {
                return "Теперь вам доступны комманды, используйте help для их просмотра";
            }
        }
        return "";
    }
}