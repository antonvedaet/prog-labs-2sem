package app.utils;
import java.util.HashMap;

import app.commands.*;
/**
 * Вспомогательный класс в котором содержиться список всех комманд
 */
public class CommandHelper {
    public HashMap<String, String> commandList(){

        HashMap<String, String> map= new HashMap<String, String>();
        PersonCreator personCreator = new PersonCreator();
        CollectionHandler collectionHandler = new CollectionHandler();
        FileManager fileManager = new FileManager();
        HashMap<String, AbstractCommand> mapp= new HashMap<String, AbstractCommand>();


        AbstractCommand info = new Info(collectionHandler);
        AbstractCommand show = new Show(collectionHandler);
        AbstractCommand add = new Add(personCreator, collectionHandler);
        AbstractCommand exit = new Exit();
        AbstractCommand removeById = new RemoveById(collectionHandler);
        AbstractCommand update = new Update(personCreator, collectionHandler);
        AbstractCommand clear = new Clear(collectionHandler);
        AbstractCommand shuffle = new Shuffle(collectionHandler);
        AbstractCommand save = new Save(collectionHandler, fileManager);
        AbstractCommand reorder = new Reorder(collectionHandler);
        AbstractCommand countLessThanHeight = new CountLessThanHeight(collectionHandler);
        AbstractCommand removeGreater = new RemoveGreater(collectionHandler);
        AbstractCommand groupCountingById = new GroupCountingById(collectionHandler);
        AbstractCommand filterContainsName = new FilterContainsName(collectionHandler);
        AbstractCommand executeScript = new ExecuteScript(mapp);
        
        map.put(info.getName(), info.getDescription());
        map.put(show.getName(), show.getDescription());
        map.put(add.getName(), add.getDescription());
        map.put(exit.getName(), exit.getDescription());
        map.put(removeById.getName(), removeById.getDescription());
        map.put(update.getName(), update.getDescription());
        map.put(clear.getName(), clear.getDescription());
        map.put(shuffle.getName(), shuffle.getDescription());
        map.put(save.getName(), save.getDescription());
        map.put(reorder.getName(), reorder.getDescription());
        map.put(countLessThanHeight.getName(), countLessThanHeight.getDescription());
        map.put(removeGreater.getName(), removeGreater.getDescription());
        map.put(groupCountingById.getName(), groupCountingById.getDescription());
        map.put(filterContainsName.getName(), filterContainsName.getDescription());
        map.put(executeScript.getName(), executeScript.getDescription());
        
       
        return map;
    }
}
