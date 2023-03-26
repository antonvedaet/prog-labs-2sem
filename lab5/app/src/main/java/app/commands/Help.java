package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.CommandHelper;
import app.utils.IOHandler;
/**
 * Класс отвечающий за команду help
 */
public class Help extends AbstractCommand {
    private CommandHelper commandHelper;

    public Help(CommandHelper commandHelper) {
        super("help", "вывести справку о всех доступных командах");
        this.commandHelper = commandHelper;
    }

    public Help() {
        super("help", "вывести справку о всех доступных командах");
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
            IOHandler.println("===========");
            for (String name: commandHelper.commandList().keySet()) {
                String key = name;
                String value = commandHelper.commandList().get(name);
                IOHandler.println("\u001B[36m" + key+ "\u001B[0m" + " - " + value + "\n===========");
            }
        }
    }//WIP
}
