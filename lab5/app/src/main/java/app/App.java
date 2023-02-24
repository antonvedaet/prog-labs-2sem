package app;
import app.data.*;
import app.utils.*;
import app.commands.*;

import java.util.Arrays;
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
        HashMap<String, AbstractCommand> map= new HashMap<String, AbstractCommand>();
        map.put(help.getName(), help);
        map.put(info.getName(), info);
        map.put(show.getName(), show);

        while(true){
            IOHandler.print("> ");
            String input = scanner.nextLine()+ " )";
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            String argument = tokens[1];

            if(map.containsKey(command)){
                map.get(command).execute(argument);

            }
        }
    }
}