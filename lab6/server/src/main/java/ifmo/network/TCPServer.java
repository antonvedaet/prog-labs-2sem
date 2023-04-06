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
        while(!serverSocket.isClosed()){
            IOHandler.serverMsg("Ожидание подключения...");
            try{
                this.clientSocket = serverSocket.accept();
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
        IOHandler.serverMsg("Connection error: " + e);
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

    public Socket getClientSocket(){
        return clientSocket;
    }
}
