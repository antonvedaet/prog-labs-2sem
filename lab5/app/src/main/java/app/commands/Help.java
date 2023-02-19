package app.commands;
import app.exceptions.ElementAmountException;

public class Help extends AbstractCommand {

    public Help(String name, String description) {
        super("help", "вывести справку о всех доступных командах");
    }
    
    @Override
    public boolean execute(String arg){
        try{
            if(!arg.isEmpty()) throw new ElementAmountException();
        } catch (ElementAmountException e) {
            return true;
        } 
        return false;
    }
}
