package ifmo;

import ifmo.commands.*;
import ifmo.network.TCPClient;
import ifmo.utils.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        PersonCreator personCreator = new PersonCreator();
        CollectionHandler collectionHandler = new CollectionHandler();
        FileManager fileManager = new FileManager();
        PersonValidator personValidator = new PersonValidator(collectionHandler);
        TCPClient client = new TCPClient();
        while(true){
            IOHandler.print("> ");
            client.sendRequest(scanner.nextLine());
        }
    }
}
