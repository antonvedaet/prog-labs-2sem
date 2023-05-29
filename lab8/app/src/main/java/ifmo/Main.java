package ifmo;

import ifmo.commands.*;
import ifmo.network.TCPClient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ifmo.utils.*;

import java.util.Scanner;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, World!");

        Scene scene = new Scene(label, 200, 100);

        primaryStage.setTitle("My JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        TCPClient client = new TCPClient();
        UserHelper userHelper = new UserHelper(client);

        launch(args);
        
        //TODO : registering + login
        boolean logged_in = false;
        while(!logged_in){
            logged_in = userHelper.ask(scanner);
        }

        while(true){
            IOHandler.print("> ");
            String input = scanner.nextLine();
            IOHandler.print(client.sendRequest(input +" placeholderArg", userHelper.user));
        }
    }
}
