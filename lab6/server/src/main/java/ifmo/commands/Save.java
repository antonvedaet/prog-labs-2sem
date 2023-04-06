package ifmo.commands;

import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.FileManager;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;

import java.io.IOException;
/**
 * Класс отвечающий за команду save
 */
public class Save extends AbstractCommand{

    private CollectionHandler collectionHandler;
    FileManager fileManager;
    public Save(CollectionHandler collectionHandler, FileManager fileManager) {
        super("save", "сохраняет коллекцию в файл");
        this.collectionHandler = collectionHandler;
        this.fileManager = fileManager;
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
    public void execute(Request request){
        if(argCheck(request.getArguments())){
            try{
                fileManager.writeToJson(collectionHandler);
            } catch (IOException e){
                IOHandler.println("Ошибка при записи в файл");
            }

        }
    }
}