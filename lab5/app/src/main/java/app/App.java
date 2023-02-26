package app;
import app.utils.*;
import app.commands.*;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonCreator pc = new PersonCreator();
        CollectionHandler cHandler = new CollectionHandler();
        AbstractCommand help = new Help();
        AbstractCommand info = new Info(cHandler);
        AbstractCommand show = new Show(cHandler);
        AbstractCommand add = new Add(pc, cHandler);
        AbstractCommand exit = new Exit();
        AbstractCommand remove_by_id = new RemoveById(cHandler);
        AbstractCommand update = new Update(pc, cHandler);
        AbstractCommand clear = new Clear(cHandler);
        AbstractCommand shuffle = new Shuffle(cHandler);

        HashMap<String, AbstractCommand> map= new HashMap<String, AbstractCommand>();
        map.put(help.getName(), help);
        map.put(info.getName(), info);
        map.put(show.getName(), show);
        map.put(add.getName(), add);
        map.put(exit.getName(), exit);
        map.put(remove_by_id.getName(), remove_by_id);
        map.put(update.getName(), update);
        map.put(clear.getName(), clear);
        map.put(shuffle.getName(), shuffle);

        while(true){
            IOHandler.print("> ");
            String input = scanner.nextLine()+ " placeholderArg";
            String[] tokens = input.split(" ");
            String command = tokens[0];
            String argument = tokens[1];
            if(map.containsKey(command)){
                map.get(command).execute(argument);
            }
        }
    }
}