package ifmo;

import ifmo.network.TCPServer;

public class Main {
    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        new Thread(server).start();
    }
}