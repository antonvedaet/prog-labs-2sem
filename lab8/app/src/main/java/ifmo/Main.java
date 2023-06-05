package ifmo;

import ifmo.app.App;
import ifmo.commands.*;
import ifmo.network.TCPClient;
import ifmo.requests.Request;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ifmo.utils.*;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception {
        App.launch(App.class,args);
        // Scanner scanner = new Scanner(System.in);
        // TCPClient tcpClient = new TCPClient();
        // UserHelper userHelper = new UserHelper(client);
        // boolean logged_in = false;
        // while(!logged_in){
        //     logged_in = userHelper.ask(scanner);
        // }

        // while(true){
        //     IOHandler.print("> ");
        //     String input = scanner.nextLine();
        //     IOHandler.print(client.sendRequest(input +" placeholderArg", userHelper.user));
        // }
    }
}
