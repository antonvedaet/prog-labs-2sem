package ifmo.network;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class OutputSocketWriter implements Runnable {

    private final Socket socket;
    private final PrintWriter outputStream;
    private String msg;

    public OutputSocketWriter(Socket socket, String msg) throws IOException {
        this.socket = socket;
        this.outputStream = new PrintWriter(socket.getOutputStream(), true);
        this.msg = msg;
    }

    @Override
    public void run() {
        outputStream.println(msg );
    }
}