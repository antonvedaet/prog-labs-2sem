package ifmo.commands;

import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.DatabaseHandler;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;
/**
 * Класс отвечающий за команду save
 */
public class Save extends AbstractCommand{

    private CollectionHandler collectionHandler;
    private DatabaseHandler databaseHandler;
    public Save(CollectionHandler collectionHandler, DatabaseHandler databaseHandler) {
        super("save", "сохраняет коллекцию в файл");
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

    // @Override
    // public void execute(Request request){
    //     if(argCheck(request.getArguments())){
    //         try{
    //             fileManager.writeToJson(collectionHandler);
    //             IOHandler.println("Коллекция успешно сохранена");
    //         } catch (IOException e){
    //             IOHandler.println("Ошибка при записи в файл");
    //         }

    //     }
    // }

    @Override
    public String execute(Request request){
        if(argCheck(request.getArguments())){
                collectionHandler.getCollection().stream().forEach(person -> databaseHandler.savePerson(person));
        }
        return "1";
    }
}
//класс который будет просто ранить экзекут каждой команды
//выдаватьь строку которая будет отправляться в кашед тред пул