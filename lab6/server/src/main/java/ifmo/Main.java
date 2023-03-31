package ifmo;

import java.util.HashMap;

import ifmo.commands.*;
import ifmo.network.TCPServer;
import ifmo.utils.*;

public class Main {
    public static void main(String[] args) {
        PersonCreator personCreator = new PersonCreator();
        CollectionHandler collectionHandler = new CollectionHandler();
        FileManager fileManager = new FileManager();
        CommandHelper commandHelper = new CommandHelper();
        PersonValidator personValidator = new PersonValidator(collectionHandler);

        collectionHandler.loadCollection();
        personValidator.checkCollectionValidity();

        TCPServer server = new TCPServer();


        Command help = new Help(commandHelper);
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

        HashMap<String, Command> map= new HashMap<String, Command>();
        map.put(help.getName(), help);
        map.put(info.getName(), info);
        map.put(show.getName(), show);
        map.put(add.getName(), add);
        map.put(exit.getName(), exit);
        map.put(removeById.getName(), removeById);
        map.put(update.getName(), update);
        map.put(clear.getName(), clear);
        map.put(shuffle.getName(), shuffle);
        map.put(save.getName(), save);
        map.put(reorder.getName(), reorder);
        map.put(countLessThanHeight.getName(), countLessThanHeight);
        map.put(removeGreater.getName(), removeGreater);
        map.put(groupCountingById.getName(), groupCountingById);
        map.put(filterContainsName.getName(), filterContainsName);

        Command executeScript = new ExecuteScript(map);
        map.put(executeScript.getName(), executeScript);

        server.start(map,collectionHandler);
    }
}