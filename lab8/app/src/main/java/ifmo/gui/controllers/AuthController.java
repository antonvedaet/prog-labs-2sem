package ifmo.gui.controllers;
import ifmo.data.User;
import ifmo.network.TCPClient;
import ifmo.utils.UserHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AuthController {
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    private TCPClient _tcpClient;

    public AuthController(TCPClient tcpClient)
    {
        _tcpClient = tcpClient;
    }

    @FXML
    public void handleButton(ActionEvent event) {

        String login = loginField.getText();
        String password = passwordField.getText();
        User user = new User(login, password);
        UserHelper userHelper = new UserHelper(_tcpClient);

        if (event.getSource() == loginButton) {
            userHelper.sendLogin(user);
        } else if (event.getSource() == registerButton) {
            userHelper.sendRegister(user);
        }
    }
}