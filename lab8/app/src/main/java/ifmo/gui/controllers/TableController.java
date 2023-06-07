package ifmo.gui.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import ifmo.data.Coordinates;
import ifmo.data.Location;
import ifmo.data.Person;
import ifmo.data.DisplayPerson;
import ifmo.network.TCPClient;
import ifmo.requests.Request;
import ifmo.utils.Deserializator;
import ifmo.utils.UserHelper;
import ifmo.data.Color;

public class TableController {

    TCPClient tcpClient;

    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

    @FXML
    private TableView<DisplayPerson> tableView;

    @FXML
    private TableColumn<DisplayPerson, String> idColumn;
    @FXML
    private TableColumn<DisplayPerson, String> nameColumn;
    @FXML
    private TableColumn<DisplayPerson, String> latitudeColumn;
    @FXML
    private TableColumn<DisplayPerson, String> longitudeColumn;
    @FXML
    private TableColumn<DisplayPerson, LocalDate> creationDateColumn;
    @FXML
    private TableColumn<DisplayPerson, Float> heightColumn;
    @FXML
    private TableColumn<DisplayPerson, LocalDateTime> birthdayColumn;
    @FXML
    private TableColumn<DisplayPerson, Color> eyeColorColumn;
    @FXML
    private TableColumn<DisplayPerson, Color> hairColorColumn;
    @FXML
    private TableColumn<DisplayPerson, String> locationColumn;
    @FXML
    private TableColumn<DisplayPerson, String> creatorColumn;

    TableController(TCPClient tcpClient){
        this.tcpClient = tcpClient;
    }

    public void initialize() {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LinkedList<DisplayPerson> linkedList = null;
                try {
                    linkedList = tcpClient.loadCollection();

                    var people = FXCollections.observableArrayList(linkedList);
                    idColumn.setCellValueFactory(data -> data.getValue().getIdProperty().asString());
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

    //Remove
    @FXML
    private void handleRemoveById() {

        TextInputDialog toRemove = new TextInputDialog();
        toRemove.setHeaderText(null);
        toRemove.setContentText("Введите id элемента который вы хотите удалить");
        Optional<String> res = toRemove.showAndWait();

        if (!res.isPresent() || res.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Введите id.");
            alert.showAndWait();
            return;
        }

        try {
            tcpClient.sendRequest(new Request("remove_by_id", res.get(), null, UserHelper.logged_user));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ADD

        @FXML
    private void handleAddButton() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setHeaderText(null);
        nameDialog.setContentText("Please enter a name:");
        Optional<String> nameResult = nameDialog.showAndWait();

        if (!nameResult.isPresent() || nameResult.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Name cannot be null or empty.");
            alert.showAndWait();
            return;
        }

        Coordinates coordinates = null;
        boolean isCoordinatesEntered = false;
        while (!isCoordinatesEntered) {
            TextInputDialog xCoordDialog = new TextInputDialog();
            xCoordDialog.setHeaderText(null);
            xCoordDialog.setContentText("Please enter the X coordinate:");
            Optional<String> xCoordResult = xCoordDialog.showAndWait();

            TextInputDialog yCoordDialog = new TextInputDialog();
            yCoordDialog.setHeaderText(null);
            yCoordDialog.setContentText("Please enter the Y coordinate:");
            Optional<String> yCoordResult = yCoordDialog.showAndWait();

            if (xCoordResult.isPresent() && !xCoordResult.get().isEmpty() &&
                    yCoordResult.isPresent() && !yCoordResult.get().isEmpty()) {
                try {
                    int x = Integer.parseInt(xCoordResult.get());
                    long y = Long.parseLong(yCoordResult.get());
                    coordinates = new Coordinates(x, y);
                    isCoordinatesEntered = true;
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid coordinate values entered. Please enter numeric values.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Both X and Y coordinates are required.");
                alert.showAndWait();
            }
        }

        Float height = null;
        boolean isHeightEntered = false;
        while (!isHeightEntered) {
            TextInputDialog heightDialog = new TextInputDialog();
            heightDialog.setHeaderText(null);
            heightDialog.setContentText("Please enter the height:");
            Optional<String> heightResult = heightDialog.showAndWait();

            if (heightResult.isPresent() && !heightResult.get().isEmpty()) {
                try {
                    float h = Float.parseFloat(heightResult.get());
                    if (h > 0) {
                        height = h;
                        isHeightEntered = true;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Height must be greater than 0.");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid height value entered. Please enter a numeric value.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Height is required.");
                alert.showAndWait();
            }
        }

        LocalDateTime birthday = null;
        boolean isBirthdayEntered = false;
        while (!isBirthdayEntered) {
            TextInputDialog birthdayDialog = new TextInputDialog();
            birthdayDialog.setHeaderText(null);
            birthdayDialog.setContentText("Please enter the birthday in the format yyyy-MM-ddTHH:mm:ss:");
            Optional<String> birthdayResult = birthdayDialog.showAndWait();

            if (birthdayResult.isPresent() && !birthdayResult.get().isEmpty()) {
                try {
                    LocalDateTime bday = LocalDateTime.parse(birthdayResult.get());
                    birthday = bday;
                    isBirthdayEntered = true;
                } catch (DateTimeParseException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid birthday value entered. Please enter a valid date and time in the format yyyy-MM-ddTHH:mm:ss.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Birthday is required.");
                alert.showAndWait();
            }
        }

        ChoiceDialog<Color> eyeColorDialog = new ChoiceDialog<>(Color.BLUE, Color.BLACK);
        eyeColorDialog.setHeaderText(null);
        eyeColorDialog.setContentText("Please choose the eye color:");
        Optional<Color> eyeColorResult = eyeColorDialog.showAndWait();

        if (!eyeColorResult.isPresent()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Eye color is required.");
            alert.showAndWait();
            return;
        }
        
        ChoiceDialog<Color> hairColorDialog = new ChoiceDialog<>(Color.BLUE, Color.BLACK);
        hairColorDialog.setHeaderText(null);
        hairColorDialog.setContentText("Please choose the hair color (optional):");
        Optional<Color> hairColorResult = hairColorDialog.showAndWait();
        
        Location location = null;
        boolean isLocationEntered = false;
        while (!isLocationEntered) {
            TextInputDialog xLocDialog = new TextInputDialog();
            xLocDialog.setHeaderText(null);
            xLocDialog.setContentText("Please enter the X location:");
            Optional<String> xLocResult = xLocDialog.showAndWait();
        
            TextInputDialog yLocDialog = new TextInputDialog();
            yLocDialog.setHeaderText(null);
            yLocDialog.setContentText("Please enter the Y location:");
            Optional<String> yLocResult = yLocDialog.showAndWait();
        
            TextInputDialog zLocDialog = new TextInputDialog();
            zLocDialog.setHeaderText(null);
            zLocDialog.setContentText("Please enter the Z location:");
            Optional<String> zLocResult = zLocDialog.showAndWait();

            TextInputDialog nameLocDialog = new TextInputDialog();
            nameLocDialog.setHeaderText(null);
            nameLocDialog.setContentText("Please enter the location name:");
            Optional<String> nameLocResult = nameLocDialog.showAndWait();
        
            if (xLocResult.isPresent() && !xLocResult.get().isEmpty() &&
                    yLocResult.isPresent() && !yLocResult.get().isEmpty() &&
                    zLocResult.isPresent() && !zLocResult.get().isEmpty() && nameLocResult.isPresent()) {
                try {
                    int x = Integer.parseInt(xLocResult.get());
                    Double y = Double.parseDouble(yLocResult.get());
                    Double z = Double.parseDouble(zLocResult.get());
                    String name =nameLocResult.get();

                    location = new Location(x, y, z, name);
                    isLocationEntered = true;
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid location values entered. Please enter numeric values.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("All X, Y, and Z locations are required.");
                alert.showAndWait();
            }
        }
        try {
            tcpClient.sendRequest(new Request("add", "placeholderArg", new Person(0 ,nameResult.get(), coordinates, LocalDate.now(), height, birthday, eyeColorResult.get(), hairColorResult.get(), location, UserHelper.logged_user.getLogin()), UserHelper.logged_user));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}