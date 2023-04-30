package ifmo.commands;
import java.io.IOException;
import java.io.PrintWriter;

import ifmo.exceptions.ElementAmountException;
import ifmo.network.TCPServer;
import ifmo.requests.Request;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
/**
 * Класс отвечающий за команду count_less_than_height
 */
public class CountLessThanHeight extends AbstractCommand{

    private CollectionHandler collectionHandler;
    private TCPServer server;

    public CountLessThanHeight(CollectionHandler collectionHandler, TCPServer server) {
        super("count_less_than_height", "выводит количество элементов, значение поля height которых меньше заданного");
        this.collectionHandler = collectionHandler;
        this.server = server;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(arg.equals("placeholderArg")) throw new ElementAmountException();
            Integer.parseInt(arg);
            
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        } catch (NumberFormatException ex) {
            IOHandler.println("Введите число");
        }
        return false;
    }

    @Override
    public void execute(Request request){
        if(argCheck(request.getArguments())){
            int count = (int) collectionHandler.getCollection().stream()
            .filter(person -> person.getHeight() < Integer.parseInt(request.getArguments()))
            .count();
            try{
                PrintWriter output = new PrintWriter(server.getClientSocket().getOutputStream(), true);
                output.println("Количество элементов с значением height меньше введенного: "+count);
            } catch (IOException ioe){
                IOHandler.println(ioe.getMessage());
            }
        }
    }
}