package ifmo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Auth extends AnchorPane {

    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    public Auth() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResourceAsStream("/home/anton/itmo/sem2/prog-labs-2sem/lab8/app/src/main/java/ifmo/view/Auth.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    // Optional method to set initial values for the email and password fields
    public void setInitialValues(String email, String password) {
        emailField.setText(email);
        passwordField.setText(password);
    }
}