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
import javafx.scene.Group;
import javafx.scene.Node;

public class VisualizationController {

    @FXML private ImageView mapView;
    @FXML private Button backToTable;

    TCPClient tcpClient;

    VisualizationController(TCPClient tcpClient){
        this.tcpClient = tcpClient;
    }


    public void initialize() {
        LinkedList<DisplayPerson> persons = null;
        try {
            persons = tcpClient.loadCollection();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Image image = new Image(getClass().getClassLoader().getResource("map.png").toExternalForm());

        setImage(image);

        for (DisplayPerson person : persons) {
            drawCircles(person);
        }
    }


    public void drawCircles(DisplayPerson person) {
        double x = person.getCoordinates().getX();
        double y = person.getCoordinates().getY();
        Circle circle = new Circle(x, y, 20, Color.RED);
        Pane parent = (Pane) mapView.getParent();
        circle.setTranslateZ(100);
        parent.getChildren().add(circle);
    }

    public void setImage(Image image) {
        mapView.setImage(image);
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
