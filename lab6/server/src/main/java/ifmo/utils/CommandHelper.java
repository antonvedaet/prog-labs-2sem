package ifmo.utils;

import ifmo.commands.*;

import java.io.Serializable;
import java.util.HashMap;
/**
 * Вспомогательный класс в котором содержиться список пар (название команды, описание команды)
 */
public class CommandHelper implements Serializable {
    public HashMap<String, String> commandList(){

        HashMap<String, String> map= new HashMap<String, String>();
        PersonCreator personCreator = new PersonCreator();
        CollectionHandler collectionHandler = new CollectionHandler();
        FileManager fileManager = new FileManager();
        HashMap<String, Command> mapp= new HashMap<String, Command>();

        Command info = new Info(collectionHandler);
        Command show = new Show(collectionHandler);
        Command add = new Add(personCreator, collectionHandler);
        Command exit = new Exit();
        Command removeById = new RemoveById(collectionHandler);
        Command update = new Update(personCreator, collectionHandler);
        Command clear = new Clear(collectionHandler);
        Command shuffle = new Shuffle(collectionHandler);
        Command save = new Save(collectionHandler, fileManager);
        Command reorder = new Reorder(collectionHandler);
        Command countLessThanHeight = new CountLessThanHeight(collectionHandler);
        Command removeGreater = new RemoveGreater(collectionHandler);
        Command groupCountingById = new GroupCountingById(collectionHandler);
        Command filterContainsName = new FilterContainsName(collectionHandler);
        Command executeScript = new ExecuteScript(mapp);
        
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
