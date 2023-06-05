package ifmo.commands;
import java.io.PrintWriter;
import java.io.StringWriter;

import ifmo.exceptions.ElementAmountException;
import ifmo.utils.CollectionHandler;
import ifmo.utils.Serializator;
import ifmo.requests.Request;
/**
 * Класс отвечающий за команду show
 */
public class Load extends AbstractCommand {
    private CollectionHandler collectionHandler;
    
    public Load(CollectionHandler collectionHandler) {
        super("load", "");
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
            Serializator s = new Serializator();
            String json = s.serializeToJson(collectionHandler);
            return json;
        } else {
            return "";
        }
    }
    
}