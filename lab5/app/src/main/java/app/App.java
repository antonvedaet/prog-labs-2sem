package app;
import app.utils.*;
import app.commands.*;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Главный класс приложения, создающий отдельный список комманд и запускающий интерактивный консольный режим
 * @author Антон Ведерников
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonCreator personCreator = new PersonCreator();
        CollectionHandler collectionHandler = new CollectionHandler();
        FileManager fileManager = new FileManager();
        CommandHelper commandHelper = new CommandHelper();
        collectionHandler.loadCollection();
        

        AbstractCommand help = new Help(commandHelper);
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

        HashMap<String, AbstractCommand> map= new HashMap<String, AbstractCommand>();
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

        AbstractCommand executeScript = new ExecuteScript(map);
        map.put(executeScript.getName(), executeScript);

        while(true){
            IOHandler.print("> ");
            String input = scanner.nextLine()+ " placeholderArg";
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            String argument = tokens[1];
            if(map.containsKey(command)){
                map.get(command).execute(argument);
            } else {
                IOHandler.println("Команда "+ command + " не найдена");
            }
        }
    }
}