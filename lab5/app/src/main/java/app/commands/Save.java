package app.commands;
import java.io.IOException;

import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
import app.utils.CollectionHandler;
import app.utils.FileManager;
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
    public void execute(String arg){
        if(argCheck(arg)){
            try{
                fileManager.writeToJson(collectionHandler);
            } catch (IOException e){
                IOHandler.println("Ошибка при записи в файл");
            }

        }
    }
}