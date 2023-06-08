package ifmo.gui.controllers;

import java.io.IOException;
import java.util.LinkedList;

import ifmo.data.DisplayPerson;
import ifmo.network.TCPClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Node;

public class VisualizationController {

    @FXML private ImageView mapView;
    @FXML private Button backToTable;

    TCPClient tcpClient;
    LinkedList<DisplayPerson> persons;

    VisualizationController(TCPClient tcpClient, LinkedList<DisplayPerson> persons){
        this.tcpClient = tcpClient;
        this.persons = persons;
    }
    
    @FXML
    public void handleBackToTable(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Table.fxml"));
        loader.setController(new TableController(tcpClient));
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
}
