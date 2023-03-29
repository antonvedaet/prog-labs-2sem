package ifmo;

import ifmo.data.Person;
import ifmo.utils.CollectionHandler;

import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 3333;
        Socket socket = new Socket(host, port);
        CollectionHandler collectionHandler = new CollectionHandler();
//        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ObjectInput objectInput = new ObjectInputStream(socket.getInputStream());
        collectionHandler.addPerson((Person) objectInput.readObject());
        collectionHandler.printPersonList();
//        output.close();
    }
}
