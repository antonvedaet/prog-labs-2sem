package ifmo;

import ifmo.data.Person;
import ifmo.network.TCPClient;
import ifmo.utils.CollectionHandler;

import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        CollectionHandler collectionHandler = new CollectionHandler();
        TCPClient client = new TCPClient();
        client.loadCollection(collectionHandler);
        collectionHandler.printPersonList();
    }
}
