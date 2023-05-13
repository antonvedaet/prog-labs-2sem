package ifmo.utils;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import ifmo.commands.Command;
import ifmo.requests.Request;;

public class Executor implements Runnable {

    HashMap<String, Command> map;
    Socket socket;
    ExecutorService executorService;
    BlockingQueue<String> messageQueue;
    BlockingQueue<Request> requestQueue;


    public Executor(HashMap<String, Command> map, Socket clientSocket, ExecutorService executorService, BlockingQueue<Request> requestQueue, BlockingQueue<String> messageQueue){
        this.map = map;
        this.socket = clientSocket;
        this.executorService = executorService;
        this.requestQueue = requestQueue;
        this.messageQueue = messageQueue;
    }
    @Override
    public void run() {
        Request request;
        try {
            request = requestQueue.take();
            if(map.containsKey(request.getCommandName())){
                String toOut = map.get(request.getCommandName()).execute(request);
                messageQueue.put(toOut);
            } 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }  
}
