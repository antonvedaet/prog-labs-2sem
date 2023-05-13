package ifmo.commands;
import java.io.PrintWriter;
import java.io.StringWriter;

import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.requests.Request;
/**
 * Класс отвечающий за команду show
 */
public class Show extends AbstractCommand {
    private CollectionHandler collectionHandler;
    
    public Show(CollectionHandler collectionHandler) {
        super("show", "вывести все элементы коллекции");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean argCheck(String arg){
        try{
            if(!arg.equals("placeholderArg")) throw new ElementAmountException();
            return true;
        } catch (ElementAmountException e) {} 
        return false;
    }

    @Override
    public String execute(Request request) {
        if (argCheck(request.getArguments())) {
            StringWriter sw = new StringWriter();
            PrintWriter output = new PrintWriter(sw, true);
    
            collectionHandler.printPersonList(output);
    
            return sw.toString();
        } else {
            return "Invalid arguments";
        }
    }
    
}