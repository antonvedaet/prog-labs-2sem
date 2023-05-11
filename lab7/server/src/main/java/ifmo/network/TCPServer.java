package ifmo.network;

import ifmo.requests.Request;
import ifmo.utils.*;
import ifmo.commands.Command;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    public void start(HashMap<String, Command> map, CollectionHandler collectionHandler){
        openServerSocket();
        while(serverSocketChannel!=null){
            logger.log(Level.INFO,"Ожидание подключения...");
            try{
                this.clientSocket = serverSocketChannel.accept();
                logger.log(Level.FINER, "Подключение успешно");
                // processRequest(map);
                executorService.submit(new RequestHandler(map, clientSocket, logger, executorService));
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
        private ExecutorService executorService;
        
        public RequestHandler(HashMap<String, Command> map, SocketChannel clientSocket, Logger logger, ExecutorService executorService) {
            this.map = map;
            this.clientSocket = clientSocket;
            this.logger = logger;
            this.executorService = executorService;
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
            objectInput.close();
            new Thread(new Executor(request, map, clientSocket.socket(), executorService)).start();
            return true;
        }
    }
}
