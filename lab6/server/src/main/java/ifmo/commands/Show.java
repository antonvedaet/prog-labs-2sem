package ifmo.commands;
import java.io.IOException;
import java.io.PrintWriter;

import ifmo.exceptions.ElementAmountException;
import ifmo.network.TCPServer;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;
/**
 * Класс отвечающий за команду show
 */
public class Show extends AbstractCommand {
    private CollectionHandler collectionHandler;
    private TCPServer server;
    
    public Show(CollectionHandler collectionHandler, TCPServer server) {
        super("show", "вывести все элементы коллекции");
        this.collectionHandler = collectionHandler;
        this.server = server;
    }

    public Show(CollectionHandler collectionHandler) {
        super("show", "вывести все элементы коллекции");
        this.collectionHandler = collectionHandler;
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
                collectionHandler.printPersonList(output);
            } catch (IOException ioe){
                IOHandler.serverMsg(ioe.getMessage());
            }
            
        }
    }
}