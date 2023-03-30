package ifmo.network;

import ifmo.data.Person;
import ifmo.requests.Request;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer{
    private static ServerSocket serverSocket;
    private static int port = 3333;
    protected Socket clientSocket;
    public void start(){
        openServerSocket();
        while(!serverSocket.isClosed()){
            IOHandler.serverError("Ожидание подключения...");
            try{
                this.clientSocket = serverSocket.accept();
                IOHandler.println("Подключение успешно");
                processRequest();
            } catch (IOException | ClassNotFoundException ioe) {
                IOHandler.serverError("Не удалось подключиться к клиенту: " + ioe.getMessage());
            }
        }
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
            throw new RuntimeException("Не получается открыть порт 3333", e);
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

    private boolean processRequest() throws IOException, ClassNotFoundException {
        ObjectInput objectInput = new ObjectInputStream(clientSocket.getInputStream());
        Request request = (Request) objectInput.readObject();
        request.getCommandName().execute(request.getArguments().split("\\s+")[0]);
        objectInput.close();
        return true;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
