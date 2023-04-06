package ifmo.commands;
import java.io.IOException;
import java.io.PrintWriter;

import ifmo.requests.Request;
import ifmo.exceptions.ElementAmountException;
import ifmo.network.TCPServer;
import ifmo.utils.CommandHelper;
import ifmo.utils.IOHandler;
/**
 * Класс отвечающий за команду help
 */
public class Help extends AbstractCommand {
    private CommandHelper commandHelper;
    private TCPServer server;

    public Help(CommandHelper commandHelper, TCPServer server) {
        super("help", "вывести справку о всех доступных командах");
        this.commandHelper = commandHelper;
        this.server = server;
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
    public void execute(Request request){
        if(argCheck(request.getArguments())){
            try{
                PrintWriter output = new PrintWriter(server.getClientSocket().getOutputStream(), true);
                output.println("===========");
                for (String name: commandHelper.commandList().keySet()) {
                    String value = commandHelper.commandList().get(name);
                    output.println("\u001B[36m" + name + "\u001B[0m" + " - " + value + "\n===========");
                } 
            } catch(IOException ioe){
                IOHandler.serverMsg(ioe.getMessage());
            }
        }
    }
}
