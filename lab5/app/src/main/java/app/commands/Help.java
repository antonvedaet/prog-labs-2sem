package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.IOHandler;

public class Help extends AbstractCommand {

    public Help(String name, String description) {
        super("help", "вывести справку о всех доступных командах");
    }
    
    @Override
    public boolean execute(String arg){
        try{
            if(!arg.isEmpty()) throw new ElementAmountException();
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        } 
        return false;
    }
}
