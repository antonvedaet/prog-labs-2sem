package ifmo.network;

import ifmo.commands.AbstractCommand;
import ifmo.commands.Add;
import ifmo.data.Person;
import ifmo.utils.CollectionHandler;
import ifmo.utils.IOHandler;
import ifmo.utils.PersonCreator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable{
    private static ServerSocket serverSocket;
    private static int PORT = 3333;
    private Thread thread = null;

    @Override
    public void run() {
        synchronized(this){
            this.thread = Thread.currentThread();
        }
        openServerSocket();

        while(!serverSocket.isClosed()) {
            Socket clientSocket;
            try {
                IOHandler.serverError("ждем подключения...");
                clientSocket = this.serverSocket.accept();
                IOHandler.serverError(clientSocket.isConnected());
            } catch (IOException e) {
                if (serverSocket.isClosed()) {
                    IOHandler.serverError("Сервер закрыт");
                    return;
                }
                throw new RuntimeException("Ошибка при полключении к клиенту", e);
            }
            CollectionHandler ch = new CollectionHandler();
            AbstractCommand add = new Add(new PersonCreator(), ch);
            add.execute("placeholderArg");
            for (Person person: ch.getCollection()) {
            }
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.PORT);
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
}
