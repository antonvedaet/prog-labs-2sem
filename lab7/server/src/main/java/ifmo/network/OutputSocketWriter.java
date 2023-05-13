package ifmo.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class OutputSocketWriter implements Runnable {

    private final Socket socket;
    private String msg;
    BlockingQueue<String> messageQueue;
    BlockingQueue<Throwable> errorQueue;

    public OutputSocketWriter(Socket socket, BlockingQueue<String> messageQueue, BlockingQueue<Throwable> errorQueue){
        this.socket = socket;
        this.messageQueue = messageQueue;
        this.errorQueue = errorQueue;
    }

    @Override
    public void run() {
        try {
            this.msg = messageQueue.take();
            if (!socket.isClosed()) {
                PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
                outputStream.println(msg);
            } else {
                System.out.println("Сокет закрыт");
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}