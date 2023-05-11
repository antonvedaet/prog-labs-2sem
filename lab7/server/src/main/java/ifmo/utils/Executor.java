package ifmo.utils;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import ifmo.commands.Command;
import ifmo.network.OutputSocketWriter;
import ifmo.requests.Request;;

public class Executor implements Runnable {

    Request request;
    HashMap<String, Command> map;
    Socket socket;
    ExecutorService executorService;


    public Executor(Request request, HashMap<String, Command> map, Socket clientSocket, ExecutorService executorService){
        this.request = request;
        this.map = map;
        this.socket = clientSocket;
        this.executorService = executorService;
    }
    @Override
    public void run() {
        if(map.containsKey(request.getCommandName())){
            String toOut = map.get(request.getCommandName()).execute(request);
            if(!toOut.equals("1")){
                try{
                    executorService.submit(new OutputSocketWriter(socket, toOut));
                } catch (IOException ioe){
                    System.out.println(ioe.getMessage());
                }
            }
        } 
    }
    
}
