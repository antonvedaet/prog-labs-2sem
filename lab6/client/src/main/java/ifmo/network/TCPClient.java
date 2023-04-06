package ifmo.network;

import ifmo.commands.Command;
import ifmo.data.Person;
import ifmo.requests.Request;
import ifmo.utils.IOHandler;
import ifmo.utils.PersonCreator;
import ifmo.utils.CollectionHandler;
import java.nio.charset.StandardCharsets;

import java.net.Socket;
import java.nio.channels.SocketChannel;
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

    //TODO: all below
    public void sendRequest(String input) throws IOException, InterruptedException {
        input +=" placeholderArg";
        String[] tokens = input.split("\\s+");
        String command = tokens[0];
        String argument = tokens[1];
        if(connectToServer()){
            ObjectOutput objectOutput = new ObjectOutputStream(this.clientSocket.getOutputStream());
            InputStream in = new BufferedInputStream(clientSocket.getInputStream());
            if(command.equals("add") || command.equals("update")){
                objectOutput.writeObject(new Request(command, argument, new PersonCreator().personCreate()));
            } else {
                objectOutput.writeObject(new Request(command, argument, null));
            }
            String str_in = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            IOHandler.print(str_in);
            in.close();
            objectOutput.close();
        }
        closeConnection();
    }

    public void receiveAnswer() throws IOException{
    }

}
