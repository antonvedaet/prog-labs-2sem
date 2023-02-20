package app.commands;
import app.exceptions.ElementAmountException;
import app.utils.CommandHandler;
import app.utils.IOHandler;

public class Help extends AbstractCommand {
    CommandHandler commandHandler;
    public Help(CommandHandler commandHandler) {
        super("help", "вывести справку о всех доступных командах");
        this.commandHandler = commandHandler;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(!arg.isBlank()) throw new ElementAmountException();
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        } 
        return false;
    }

    @Override
    public void execute(String arg){
        if(argCheck(arg)){
            IOHandler.println("выводит список всех команд и описания");//связать с CommandHandler'ом когда он будет готов
        }
    }//WIP
}
