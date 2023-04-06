package ifmo.network;

import ifmo.data.Person;
import ifmo.requests.Request;
import ifmo.utils.*;
import ifmo.commands.Command;

import java.util.HashMap;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TCPServer{
    private ServerSocketChannel serverSocketChannel;
    private int port = 3333;
    protected SocketChannel clientSocket;

    public void start(HashMap<String, Command> map, CollectionHandler collectionHandler){
        openServerSocket();
        while(serverSocketChannel!=null){
            IOHandler.serverMsg("Ожидание подключения...");
            try{
                this.clientSocket = serverSocketChannel.accept();
                IOHandler.serverMsg("Подключение успешно");
                processRequest(map);
            } catch (IOException ioe) {
                IOHandler.serverMsg("Не удалось подключиться к клиенту: " + ioe.getMessage());
            } catch (ClassNotFoundException ioe) {
                IOHandler.serverMsg("Ошибка в полученном запросе: " + ioe.getMessage());
            }
        }
        closeServerSocket();
    }

    private void openServerSocket() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", port));
        } catch (IOException e) {
            throw new RuntimeException("Не получается открыть порт 3333", e);
        }
    }

    private void closeServerSocket() {
        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException("Не получается закрыть порт 3333", e);
        }
    }

    private void sendPerson(Socket clientSocket, Person person) {
        try {
            ObjectOutput objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutput.writeObject(person);
            objectOutput.close();
    } catch (IOException e){
        IOHandler.serverMsg("Connection error: " + e);
        }
    }

    private boolean processRequest(HashMap<String, Command> map) throws IOException, ClassNotFoundException {
        ObjectInput objectInput = new ObjectInputStream(clientSocket.socket().getInputStream());
        Request request = (Request) objectInput.readObject();
        if(map.containsKey(request.getCommandName())){
            map.get(request.getCommandName()).execute(request);
        } else {
            IOHandler.println("Такой команды не существует");
        }
        objectInput.close();
        return true;
    }

    public Socket getClientSocket(){
        return clientSocket.socket();
    }
}
