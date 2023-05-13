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
    BlockingQueue<String> messageQueue;
    BlockingQueue<Request> requestQueue;
    BlockingQueue<Throwable> errorQueue;


    public Executor(HashMap<String, Command> map, Socket clientSocket, BlockingQueue<Request> requestQueue, BlockingQueue<String> messageQueue, BlockingQueue<Throwable> errorQueue){
        this.map = map;
        this.socket = clientSocket;
        this.requestQueue = requestQueue;
        this.messageQueue = messageQueue;
        this.errorQueue = errorQueue;
    }
    @Override
    public void run() {
        Request request;
        try {
            request = requestQueue.take();
            if(map.containsKey(request.getCommandName()) && errorQueue.peek()==null){
                String toOut = map.get(request.getCommandName()).execute(request);
                messageQueue.put(toOut);
            } else {
                messageQueue.put("Ошибка при обработке запроса");
                errorQueue.clear();
            }
        } catch (Throwable e) {
           try {
            errorQueue.put(e);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        }
    }  
}
