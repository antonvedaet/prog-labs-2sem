package ifmo.app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

import ifmo.gui.controllers.AuthController;
import ifmo.network.TCPClient;
import javafx.stage.Stage;

public class App extends Application { 

    @Override
    public void start(Stage primaryStage) {
        TCPClient tcpClient = new TCPClient();
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"));
            fxmlLoader.setController(new AuthController(tcpClient));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
