package ifmo.commands;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;
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
    public String execute(Request request){
        if(argCheck(request.getArguments())){
            System.exit(0);
        }
        return "";
    }//WIP
}