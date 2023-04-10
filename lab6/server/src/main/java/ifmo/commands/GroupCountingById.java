package ifmo.commands;

import ifmo.data.Person;
import ifmo.exceptions.ElementAmountException;
import ifmo.network.TCPServer;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
import ifmo.requests.Request;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс отвечающий за команду group_counting_by_id
 */
public class GroupCountingById extends AbstractCommand {

    private CollectionHandler collectionHandler;
    private TCPServer server;

    public GroupCountingById(CollectionHandler collectionHandler, TCPServer server) {
        super("group_counting_by_id", "сгруппировывает элементы коллекции по значению поля id, выводит количество элементов в каждой группе");
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
            collectionHandler.getCollection().stream()
            .map(Person::getId)
            .distinct()
            .forEach(id -> {
                long count = collectionHandler.getCollection().stream()
                    .filter(person -> person.getId()==id)
                    .count();
                    try{
                        PrintWriter output = new PrintWriter(server.getClientSocket().getOutputStream(), true);
                        output.println("Количество элементов с id=" + id + " : " + count);
                    } catch (IOException ioe){
                        IOHandler.println(ioe.getMessage());
                    }
            });
        }
    }//WIP
}
