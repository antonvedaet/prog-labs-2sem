package ifmo.gui.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

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

    // Set up the table with sample data
    public void initialize() {

        LinkedList<Person> linkedList = null;
        try {
            linkedList = tcpClient.loadCollection();
            System.out.println(linkedList.getLast().getNameProperty());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        var people = FXCollections.observableArrayList(linkedList);

        // Set up each TableColumn to display the relevant data from the Person objects
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

        // Add the list of person objects to the TableView
        tableView.setItems(people);
    }

    // Handle button events
    @FXML
    private void handleProfileButton() {
        // Code to open the user's profile could go here
        System.out.println("Opening user profile...");
    }

    @FXML
    private void handleVisualizationButton() {
        // Code to open the visualization could go here
        System.out.println("Opening visualization...");
    }
}