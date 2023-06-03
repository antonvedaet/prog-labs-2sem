package ifmo.gui.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AuthController {
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    @FXML
    public void handleButton(ActionEvent event) {
        if (event.getSource() == loginButton) {
            System.out.println("login");
        } else if (event.getSource() == registerButton) {
            System.out.println("register");
        }
    }
}