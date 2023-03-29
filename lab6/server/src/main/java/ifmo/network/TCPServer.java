package ifmo.network;

import ifmo.data.Person;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer{
    private static ServerSocket serverSocket;
    private static int port = 3333;

    protected Socket clientSocket;
    public void start(){
        openServerSocket();
        Scanner scanner = new Scanner(System.in);
        while(!serverSocket.isClosed()){
            try{
                this.clientSocket = serverSocket.accept();
                IOHandler.println("Connection succesful!");
                CollectionHandler collectionHandler = new CollectionHandler();
                collectionHandler.loadCollection();
                sendCollection(clientSocket ,collectionHandler);
            } catch (IOException ioe) {
                IOHandler.serverError("Неудалось подключиться к клиенту: " + ioe.getMessage());
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
    } catch (IOException e){
        IOHandler.serverError("Connection error: " + e);
        }
    }

    public void sendCollection(Socket clientSocket, CollectionHandler collectionHandler){
        try {
            ObjectOutput objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutput.writeObject(collectionHandler.getCollection());
        } catch (IOException e){
            IOHandler.serverError("Connection error: " + e);
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
