package ifmo.network;

import ifmo.utils.IOHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server implements Runnable{
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
            ProcessRequest(clientSocket);
        }
    }

    private void ProcessRequest(Socket clientSocket){
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            byte[] a = "pobeda".getBytes();
            IOHandler.serverError(new String(input.readAllBytes(), StandardCharsets.UTF_8));
            IOHandler.serverError(input.read());
            output.write(a);
            output.close();
            input.close();
        } catch (IOException e){
            IOHandler.serverError("(" + e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.PORT);
        } catch (IOException e) {
            throw new RuntimeException("Не получается открыть порт 3333", e);
        }
    }
}
