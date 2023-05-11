package ifmo.commands;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import ifmo.exceptions.ElementAmountException;
import ifmo.network.TCPServer;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
import ifmo.requests.Request;
/**
 * Класс отвечающий за команду filter_contains_name
 */
public class FilterContainsName extends AbstractCommand{

    private CollectionHandler collectionHandler;
    private TCPServer server;

    public FilterContainsName(CollectionHandler collectionHandler, TCPServer server) {
        super("filter_contains_name", "выводит элементы, значение поля name которых содержит заданную подстроку");
        this.collectionHandler = collectionHandler;
        this.server = server;
    }
    
    @Override
    public boolean argCheck(String arg){
        try{
            if(arg.equals("placeholderArg")) throw new ElementAmountException();
            return true;
        } catch (ElementAmountException e) {
            IOHandler.println("Некорректное кол-во аргументов");
        }
        return false;
    }

    @Override
    public String execute(Request request) {
        if (argCheck(request.getArguments())) {
            try (StringWriter sw = new StringWriter()) {
                PrintWriter output = new PrintWriter(sw, true);
                collectionHandler.getCollection().stream()
                    .filter(person -> person.getName().contains(request.getArguments()))
                    .forEach(person -> collectionHandler.printPerson(person, output));
                return sw.toString();
            } catch (IOException ioe) {
                return "Error: " + ioe.getMessage();
            }
        } else {
            return "";
        }
    }
}