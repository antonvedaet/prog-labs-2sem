package ifmo.commands;
import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CommandHelper;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду help
 */
public class Help extends AbstractCommand {

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
            for (String name: CommandHelper.commandList().keySet()) {
                String value = CommandHelper.commandList().get(name);
                IOHandler.println("\u001B[36m" + name + "\u001B[0m" + " - " + value + "\n===========");
            }
        }
    }//WIP
}
