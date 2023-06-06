package ifmo.gui.controllers;
import java.io.IOException;
import java.net.URL;

import ifmo.data.User;
import ifmo.network.TCPClient;
import ifmo.utils.UserHelper;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AuthController {
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private StackPane popupPane;
    private TCPClient _tcpClient;
    private UserHelper userHelper;

    public AuthController(TCPClient tcpClient)
    {
        _tcpClient = tcpClient;
        this.userHelper = new UserHelper(_tcpClient);
    }

    @FXML
    public void showErrorPopup() {
        popupPane.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(event -> popupPane.setVisible(false));
        visiblePause.play();
    }


    @FXML
    public void showTable(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Table.fxml"));
        loader.setController(new TableController(_tcpClient));
        Parent root;
        try {
            root = loader.load();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene nextScene = new Scene(root);
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleButton(ActionEvent event) {

        String login = loginField.getText();
        String password = passwordField.getText();
        User user = new User(login, password);

        if (event.getSource() == loginButton) {
            if(userHelper.verify(userHelper.sendLogin(user), user)){
                showTable(event);
            } else {
                showErrorPopup();
            }
        } 
        if (event.getSource() == registerButton) {
            if(userHelper.verify(userHelper.sendRegister(user), user)){
                showTable(event);
            } else {
                showErrorPopup();
            }
        }
    }
}