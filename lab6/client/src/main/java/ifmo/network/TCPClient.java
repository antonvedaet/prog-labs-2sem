package ifmo.network;

import ifmo.data.Person;
import ifmo.utils.IOHandler;
import ifmo.utils.CollectionHandler;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class TCPClient {
    private String host = "localhost";
    private int port = 3333;

    protected Socket clientSocket;

    public boolean connectToServer() throws IOException {
        try {
            this.clientSocket = new Socket(host, port);
            return true;
        } catch (IOException ioe) {
            IOHandler.println("Ошибка при подключении: " + ioe.getMessage());
            IOHandler.println("Для повторной попытки нажмите клавишу Enter...");
            System.in.read();
            return connectToServer();
        }
    }

    public Person loadPerson() throws IOException, ClassNotFoundException {
        connectToServer();
        ObjectInput objectInput = new ObjectInputStream(clientSocket.getInputStream());
        Person person = (Person) objectInput.readObject();
        objectInput.close();
        return person;
    }

    public void loadCollection(CollectionHandler collectionHandler) throws Exception {
        connectToServer();
        ObjectInput objectInput = new ObjectInputStream(clientSocket.getInputStream());

        for(Person person: (LinkedList<Person>) objectInput.readObject()){
            collectionHandler.addPerson(person);
        }
        objectInput.close();
    }

}
