package ifmo.commands;
import java.io.IOException;
import java.io.PrintWriter;

import ifmo.exceptions.ElementAmountException;
import ifmo.network.TCPServer;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
import ifmo.requests.Request;
/**
 * Класс отвечающий за команду info
 */
public class Info extends AbstractCommand{

    private CollectionHandler collectionHandler;
    private TCPServer server;

    public Info(CollectionHandler collectionHandler, TCPServer server) {
        super("info", "вывести информацию о коллекции");
        this.collectionHandler = collectionHandler;
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
                output.println("Кол-во элементов в коллекции: "+collectionHandler.getSize());
                output.println("Дата инициализации коллекции: "+collectionHandler.getInitDate());
            } catch (IOException ioe){
                IOHandler.serverMsg(ioe.getMessage());
            }
        }
    }
}
