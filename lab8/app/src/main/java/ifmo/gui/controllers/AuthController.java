package ifmo.gui.controllers;
import java.io.IOException;
import java.net.URL;

import ifmo.data.User;
import ifmo.network.TCPClient;
import ifmo.utils.UserHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AuthController {
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    private TCPClient _tcpClient;
    private UserHelper userHelper;

    public AuthController(TCPClient tcpClient)
    {
        _tcpClient = tcpClient;
        this.userHelper = new UserHelper(_tcpClient);
    }

    @FXML
    public void handleButton(ActionEvent event) {

        String login = loginField.getText();
        String password = passwordField.getText();
        User user = new User(login, password);

        if (event.getSource() == loginButton) {
            // if(userHelper.verify(userHelper.sendRegister(user), user)){
            //     //transfer to next page
            // }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Table.fxml"));
            loader.setController(new TableController());
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
        } else if (event.getSource() == registerButton) {
            if(userHelper.verify(userHelper.sendRegister(user), user)){
                //transfer to next page
            }
        }
    }
}