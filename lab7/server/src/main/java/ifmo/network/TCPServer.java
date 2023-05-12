package ifmo.network;

import ifmo.requests.Request;
import ifmo.utils.*;
import ifmo.commands.Command;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TCPServer{
    private ServerSocketChannel serverSocketChannel;
    private int port = 3333;
    protected SocketChannel clientSocket;
    private Logger logger = Logger.getLogger("logger");
    ExecutorService executorService = Executors.newCachedThreadPool();
    BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
    BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();

    public void start(HashMap<String, Command> map, CollectionHandler collectionHandler){
        openServerSocket();
        while(serverSocketChannel!=null){
            logger.log(Level.INFO,"Ожидание подключения...");
            try{
                this.clientSocket = serverSocketChannel.accept();
                logger.log(Level.FINER, "Подключение успешно");
                executorService.submit(new RequestHandler(map, clientSocket, logger, requestQueue));//1
                new Thread(new Executor(map, clientSocket.socket(), executorService, requestQueue, messageQueue)).start();//2
                try {
                    executorService.submit(new OutputSocketWriter(clientSocket.socket(), messageQueue)); //3
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException ioe) {
                logger.log(Level.SEVERE,"Не удалось подключиться к клиенту: ", ioe.getMessage());
            }
        }
        closeServerSocket();
        executorService.shutdown();
    }

    private void openServerSocket() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", port));
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Ошибка при открытии соединения", e.getMessage());
        }
    }

    private void closeServerSocket() {
        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Ошибка при закрытии соединения", e.getMessage());
        }
    }

    public Socket getClientSocket(){
        return clientSocket.socket();
    }

    private static class RequestHandler implements Runnable {
        private HashMap<String, Command> map;
        private SocketChannel clientSocket;
        private Logger logger;
        BlockingQueue<Request> requestQueue;

        public RequestHandler(HashMap<String, Command> map, SocketChannel clientSocket, Logger logger, BlockingQueue<Request> requestQueue) {
            this.map = map;
            this.clientSocket = clientSocket;
            this.logger = logger;
            this.requestQueue = requestQueue;
        }
    
        @Override
        public void run() {
            try {
                processRequest(map);
            } catch (IOException | ClassNotFoundException e) {
                logger.log(Level.SEVERE, "Ошибка при обработке запроса: ", e.getMessage());
            }
        }
    
        private boolean processRequest(HashMap<String, Command> map) throws IOException, ClassNotFoundException {
            ObjectInput objectInput = new ObjectInputStream(clientSocket.socket().getInputStream());
            Request request = (Request) objectInput.readObject();
            // objectInput.close();
            try {
                requestQueue.put(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
