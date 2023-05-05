package ifmo.commands;

import ifmo.data.User;
import ifmo.exceptions.ElementAmountException;
import ifmo.exceptions.RecursionException;
import ifmo.network.TCPClient;
import ifmo.utils.IOHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * Класс отвечающий за команду execute_script {script-name}
 */
public class ExecuteScript extends AbstractCommand {
    private HashMap<String, String> map;
    private TCPClient client;
    private User user;
    public ExecuteScript(HashMap<String, String> map, TCPClient client, User user) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.map = map;
        this.client = client;
        this.user = user;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(arg.equals("placeholderArg")) throw new ElementAmountException();
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
            return false;
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(arg));
            String command;
            while ((command = reader.readLine()) != null) {
                command += " placeholderArg";
                String[] tokens = command.split("\\s+");
                String argument = tokens[1];
                if(argument.equals(arg)){
                    throw new RecursionException();
                    }
                }
            } catch (Exception re){
                IOHandler.println("Запуск скрипта приведет к бесконечной рекурсии");
                return false;
            }
            return true;
        }
        @Override
        public void execute(String arg){
            if(argCheck(arg)){
                try (BufferedReader reader = new BufferedReader(new FileReader(arg))) {
                    String command;
    
                    while ((command = reader.readLine()) != null) {
                        command += " placeholderArg";
                        String[] tokens = command.split("\\s+");
                        String commandName = tokens[0];
                        if(map.containsKey(commandName)){
                            try {
                                client.sendRequest(command, user);
                            } catch (InterruptedException ie){
                                IOHandler.println(ie.getMessage()); 
                            }
                        }
                    }
                } catch (IOException e) {
                    IOHandler.println(e.getMessage());
            }
        }
        }
}