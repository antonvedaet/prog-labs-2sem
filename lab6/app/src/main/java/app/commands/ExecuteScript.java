package app.commands;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import app.exceptions.ElementAmountException;
import app.exceptions.RecursionException;
import app.utils.IOHandler;
/**
 * Класс отвечающий за команду execute_script {scriptname}
 */
public class ExecuteScript extends AbstractCommand {
    HashMap<String, AbstractCommand> map;
    List<String> prevScripts;
    public ExecuteScript(HashMap<String, AbstractCommand> map) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.map = map;
        prevScripts= new ArrayList<String>();
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
                    command += " placeholderArg";
                    String[] tokens = command.split("\\s+");
                    command = tokens[0];
                    String argument = tokens[1];
                    if(!command.equals("execute_script")){
                        IOHandler.println("Выполнение команды: " + command);
                        if(map.containsKey(command)){
                        map.get(command).execute(argument);
                        } 
                    } else {
                        if(prevScripts.contains(argument)){
                            throw new RecursionException(); 
                        } else {
                            prevScripts.add(argument);
                            IOHandler.println("Выполнение команды: " + command);
                            if(map.containsKey(command)){
                            map.get(command).execute(argument);
                            } 
                        }
                    }
                }
            } catch (IOException e) {
                IOHandler.println("Ошибка при выполнении скрипта: " + arg);
            } catch (RecursionException re){
                IOHandler.println("Скрипт или цепочка скриптов не могут выполнять сами себя.");
            }
        }
        prevScripts.clear();
    }
}
