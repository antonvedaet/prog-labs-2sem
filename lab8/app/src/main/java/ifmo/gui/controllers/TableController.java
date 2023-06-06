package ifmo.gui.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import ifmo.data.Coordinates;
import ifmo.data.Location;
import ifmo.data.Person;
import ifmo.network.TCPClient;
import ifmo.requests.Request;
import ifmo.utils.Deserializator;
import ifmo.data.Color;

public class TableController {

    TCPClient tcpClient;
    
    // Reference the TableView from the FXML file
    @FXML
    private TableView<Person> tableView;

    // Reference each TableColumn from the FXML file
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, String> latitudeColumn;
    @FXML
    private TableColumn<Person, String> longitudeColumn;
    @FXML
    private TableColumn<Person, LocalDate> creationDateColumn;
    @FXML
    private TableColumn<Person, Float> heightColumn;
    @FXML
    private TableColumn<Person, LocalDateTime> birthdayColumn;
    @FXML
    private TableColumn<Person, Color> eyeColorColumn;
    @FXML
    private TableColumn<Person, Color> hairColorColumn;
    @FXML
    private TableColumn<Person, String> locationColumn;
    @FXML
    private TableColumn<Person, String> creatorColumn;

    TableController(TCPClient tcpClient){
        this.tcpClient = tcpClient;
    }

    public void initialize() {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LinkedList<Person> linkedList = null;
                try {
                    linkedList = tcpClient.loadCollection();

                    var people = FXCollections.observableArrayList(linkedList);

                    nameColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
                    latitudeColumn.setCellValueFactory(data -> data.getValue().getCoordinatesProperty().getValue().getXProperty().asString());
                    longitudeColumn.setCellValueFactory(data -> data.getValue().getCoordinatesProperty().getValue().getYProperty().asString());
                    creationDateColumn.setCellValueFactory(data -> data.getValue().getCreationDateProperty());
                    heightColumn.setCellValueFactory(data -> data.getValue().getHeightProperty().asObject());
                    birthdayColumn.setCellValueFactory(data -> data.getValue().getBirthdayProperty());
                    eyeColorColumn.setCellValueFactory(data -> data.getValue().getEyeColorProperty());
                    hairColorColumn.setCellValueFactory(data -> data.getValue().getHairColorProperty());
                    locationColumn.setCellValueFactory(data -> data.getValue().getLocationProperty().getValue().getNameProperty());
                    creatorColumn.setCellValueFactory(data -> data.getValue().getCreatorProperty());

                    tableView.setItems(people);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);
    }

    @FXML
    private void handleProfileButton() {
        System.out.println("Opening user profile...");
    }

    @FXML
    private void handleVisualizationButton() {
        System.out.println("Opening visualization...");
    }
}