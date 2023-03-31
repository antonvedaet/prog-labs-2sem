package ifmo.network;

import ifmo.data.Person;
import ifmo.requests.Request;
import ifmo.utils.*;
import ifmo.commands.Command;

import java.util.HashMap;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer{
    private ServerSocket serverSocket;
    private int port = 3333;
    protected Socket clientSocket;

    public void start(HashMap<String, Command> map, CollectionHandler collectionHandler){
        openServerSocket();
        boolean collectionLoaded = false;
        while(!serverSocket.isClosed()){
            IOHandler.serverError("Ожидание подключения...");
            try{
                this.clientSocket = serverSocket.accept();
                IOHandler.println("Подключение успешно");
                if(!collectionLoaded){
                    sendCollection(clientSocket, collectionHandler);
                    collectionLoaded = true;
                }
                processRequest(map);
            } catch (IOException ioe) {
                IOHandler.serverError("Не удалось подключиться к клиенту: " + ioe.getMessage());
            } catch (ClassNotFoundException ioe) {
                IOHandler.serverError("Ошибка в полученном запросе: " + ioe.getMessage());
            }
        }
        closeServerSocket();
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            throw new RuntimeException("Не получается открыть порт 3333", e);
        }
    }

    private void closeServerSocket() {
        try {
            this.serverSocket.close();
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
        IOHandler.serverError("Connection error: " + e);
        }
    }

    public void sendCollection(Socket clientSocket, CollectionHandler collectionHandler){
        try {
            ObjectOutput objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutput.writeObject(collectionHandler.getCollection());
            objectOutput.close();
        } catch (IOException e){
            IOHandler.serverError("Connection error: " + e);
        }
    }

    private boolean processRequest(HashMap<String, Command> map) throws IOException, ClassNotFoundException {
        ObjectInput objectInput = new ObjectInputStream(clientSocket.getInputStream());
        Request request = (Request) objectInput.readObject();
        if(map.containsKey(request.getCommandName())){
            map.get(request.getCommandName()).execute(request.getArguments().split("\\s+")[0]);
        } else {
            IOHandler.println("Такой команды не существует");
        }
        objectInput.close();
        return true;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
