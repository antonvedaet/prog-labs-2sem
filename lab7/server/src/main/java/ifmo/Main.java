package ifmo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import ifmo.commands.*;
import ifmo.data.User;
import ifmo.network.TCPServer;
import ifmo.requests.Request;
import ifmo.utils.*;

public class Main {
    public static void main(String[] args) {
        CollectionHandler collectionHandler = new CollectionHandler();
        PersonValidator personValidator = new PersonValidator(collectionHandler);
        Scanner scanner = new Scanner(System.in);

        DatabaseHandler databaseHandler = new DatabaseHandler();

        try {
            collectionHandler.setCollection(databaseHandler.getAllPersons());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        personValidator.checkCollectionValidity();

        TCPServer server = new TCPServer();

        Command info = new Info(collectionHandler);
        Command show = new Show(collectionHandler);
        Command add = new Add(collectionHandler, databaseHandler);
        Command exit = new Exit();
        Command removeById = new RemoveById(collectionHandler, databaseHandler);
        Command update = new Update(collectionHandler, databaseHandler);
        Command clear = new Clear(collectionHandler);
        Command shuffle = new Shuffle(collectionHandler);
        Command save = new Save(collectionHandler, databaseHandler);
        Command reorder = new Reorder(collectionHandler);
        Command countLessThanHeight = new CountLessThanHeight(collectionHandler);
        Command removeGreater = new RemoveGreater(collectionHandler, databaseHandler);
        Command groupCountingById = new GroupCountingById(collectionHandler);
        Command filterContainsName = new FilterContainsName(collectionHandler);
        Command register = new Register(databaseHandler);
        Command login = new Login(databaseHandler);

        HashMap<String, Command> map= new HashMap<String, Command>();
        map.put(info.getName(), info);
        map.put(show.getName(), show);
        map.put(add.getName(), add);
        map.put(removeById.getName(), removeById);
        map.put(update.getName(), update);
        map.put(clear.getName(), clear);
        map.put(shuffle.getName(), shuffle);
        map.put(reorder.getName(), reorder);
        map.put(countLessThanHeight.getName(), countLessThanHeight);
        map.put(removeGreater.getName(), removeGreater);
        map.put(groupCountingById.getName(), groupCountingById);
        map.put(filterContainsName.getName(), filterContainsName);
        map.put(register.getName(), register);
        map.put(login.getName(), login);

        Command executeScript = new ExecuteScript(map);
        map.put(executeScript.getName(), executeScript);

        new Thread(() ->{
            while(true){
                String command = scanner.nextLine();
                if(command.trim().equals(exit.getName())){
                    scanner.close();
                    save.execute(new Request("save", "placeholderArg", null, null));
                    exit.execute(new Request("exit", "placeholderArg", null, null));
                }
                if(command.trim().equals(save.getName())){
                    save.execute(new Request("save", "placeholderArg", null, null));
                } else {
                    IOHandler.serverMsg("Такой команды не существует, на сервере доступны только команды save и exit");
                }
            }
        }).start();

        new Thread(() ->{
            server.start(map,collectionHandler);
        }).start();
    }
}