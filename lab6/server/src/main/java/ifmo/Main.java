package ifmo;

import ifmo.network.TCPServer;
import ifmo.utils.CollectionHandler;

public class Main {
    public static void main(String[] args) {
        CollectionHandler collectionHandler = new CollectionHandler();
        collectionHandler.loadCollection();
        TCPServer server = new TCPServer();
        server.start();
    }
}