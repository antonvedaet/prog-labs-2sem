package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;
/**
 * Класс отвечающий за команду exit
 */
public class Exit extends AbstractCommand {
    public Exit() {
        super("exit", "выйти из программы");
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(!arg.equals("placeholderArg")) throw new ElementAmountException();
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        } 
        return false;
    }
    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            System.exit(0);
        }
    }//WIP
}