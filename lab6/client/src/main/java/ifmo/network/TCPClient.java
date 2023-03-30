package ifmo.network;

import ifmo.commands.Command;
import ifmo.data.Person;
import ifmo.requests.Request;
import ifmo.requests.RequestsHelper;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.*;
import java.util.List;

public class TCPClient {
    private String host = "localhost";
    private int port = 3333;
    private Socket clientSocket;

    public boolean connectToServer() throws IOException {
        try {
            this.clientSocket = new Socket(host, port);
            return true;
        } catch (IOException ioe) {
            IOHandler.println("Ошибка при подключении: " + ioe.getMessage());
            return false;
        }
    }

    public void closeConnection() throws IOException {
        this.clientSocket.close();
    }

    public Person loadPerson() throws IOException, ClassNotFoundException {
        connectToServer();
        ObjectInput objectInput = new ObjectInputStream(this.clientSocket.getInputStream());
        Person person = (Person) objectInput.readObject();
        objectInput.close();
        closeConnection();
        return person;
    }

    public void loadSentCollection(CollectionHandler collectionHandler) throws Exception {
        if(connectToServer()){
            ObjectInput objectInput = new ObjectInputStream(this.clientSocket.getInputStream());
            collectionHandler.setCollection((LinkedList<Person>) objectInput.readObject());
            objectInput.close();
            this.clientSocket.close();
        } else {
            IOHandler.println("Не удалось подключиться к серверу, коллекция не будет загружена.");
        }
    }
    //TODO: all below
    public void sendRequest(String input) throws IOException {
        connectToServer();
        input +=" placeholderArg";
        String[] tokens = input.split("\\s+");
        String command = tokens[0];
        String argument = tokens[1];
        if(RequestsHelper.getRequestList().containsKey(command)){
            ObjectOutput objectOutput = new ObjectOutputStream(this.clientSocket.getOutputStream());
            objectOutput.writeObject(RequestsHelper.getRequestList().get(command));
        } else {
            IOHandler.println("Комманда " + command + " не найдена");
        }
        closeConnection();
    }

    public void recieveAnswer(){

    }

}
