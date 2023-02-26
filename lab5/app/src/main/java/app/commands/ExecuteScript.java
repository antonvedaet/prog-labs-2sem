package app.commands;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;

public class ExecuteScript extends AbstractCommand {
    HashMap<String, AbstractCommand> map;
    public ExecuteScript(HashMap<String, AbstractCommand> map) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.map = map;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(arg.equals("placeholderArg")) throw new ElementAmountException();
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        }
        return false;
    }
    
    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            try (BufferedReader reader = new BufferedReader(new FileReader(arg))) {
                String command;
                while ((command = reader.readLine()) != null) {
                    IOHandler.println("Выполнение команды: " + command);
                    command += " placeholderArg";
                    String[] tokens = command.split(" ");
                    command = tokens[0];
                    String argument = tokens[1];
                    if(map.containsKey(command)){
                        map.get(command).execute(argument);
                    }
                }
            } catch (IOException e) {
                IOHandler.println("Ошибка при выполнении скрипта: " + arg);
            }
        }
    }
}
