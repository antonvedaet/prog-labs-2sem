package ifmo.app;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import ifmo.view.*;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Auth authWindow = new Auth();
        authWindow.setPrefSize(800, 600);
        Scene scene = new Scene(authWindow);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
