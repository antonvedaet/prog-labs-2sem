package ifmo.app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import ifmo.gui.controllers.AuthController;
import ifmo.network.TCPClient;
import javafx.stage.Stage;

public class App extends Application { 

    private Locale currentLocale = Locale.forLanguageTag("en-UK");
    private ResourceBundle bundle = ResourceBundle.getBundle("auth", currentLocale);
    private TCPClient tcpClient = new TCPClient();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"), bundle);
        AuthController authController = new AuthController(tcpClient);
        fxmlLoader.setController(authController);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        
        Button englishButton = new Button("English");
        englishButton.setOnAction(event -> changeLocale(Locale.forLanguageTag("en-UK"), primaryStage, scene));

        
        Button russianButton = new Button("Русский");
        russianButton.setOnAction(event -> changeLocale(new Locale("ru_RU"), primaryStage, scene));

        HBox buttonBox = new HBox(10, englishButton, russianButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        if (root instanceof Pane) {
            
            ((Pane) root).getChildren().addAll(buttonBox);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void changeLocale(Locale newLocale, Stage stage, Scene scene) {
        if (!newLocale.equals(currentLocale)) {
            currentLocale = newLocale;
            bundle = ResourceBundle.getBundle("auth", currentLocale);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"), bundle);
            AuthController authController = new AuthController(tcpClient);
            fxmlLoader.setController(authController);

            try {
                Parent root = fxmlLoader.load();

                
                Button englishButton = new Button("English");
                englishButton.setOnAction(event -> changeLocale(Locale.forLanguageTag("en-UK"), stage, scene));

                
                Button russianButton = new Button("Русский");
                russianButton.setOnAction(event -> changeLocale(new Locale("ru_RU"), stage, scene));

                HBox buttonBox = new HBox(10, englishButton, russianButton);
                buttonBox.setPadding(new Insets(10));
                buttonBox.setAlignment(Pos.CENTER_LEFT);
        
                if (root instanceof Pane) {
                     
                    ((Pane) root).getChildren().addAll(buttonBox);
                }

                scene.setRoot(root);

                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
