package ifmo.gui.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import ifmo.data.Color;
import ifmo.data.Coordinates;
import ifmo.data.DisplayPerson;
import ifmo.data.Location;
import ifmo.data.Person;
import ifmo.network.TCPClient;
import ifmo.requests.Request;
import ifmo.utils.UserHelper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TableController {

    TCPClient tcpClient;

    Locale locale;

    ResourceBundle bundle;

    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button removeGreaterButton;

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

    @FXML
    private HBox bottomHBox;


    TableController(TCPClient tcpClient, Locale locale){
        this.locale = locale;
        this.tcpClient = tcpClient;
        bundle =  ResourceBundle.getBundle("table", locale);
    }

    public void initialize() {

        Label label = new Label(UserHelper.logged_user.getLogin());
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 24;");
        bottomHBox.getChildren().add(label);


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
    private void handleLogoutButton(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("auth", locale);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"));
        loader.setController(new AuthController(tcpClient, locale));
        loader.setResources(bundle);
        Parent root;
        try {
            root = loader.load();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene nextScene = new Scene(root);

            Button englishButton = new Button("English");
            englishButton.setOnAction(pressEvent -> changeLocale(Locale.forLanguageTag("en-UK"), primaryStage, nextScene));
    
            
            Button russianButton = new Button("Русский");
            russianButton.setOnAction(pressEvent -> changeLocale(new Locale("ru_RU"), primaryStage, nextScene));
    
            HBox buttonBox = new HBox(10, englishButton, russianButton);
            buttonBox.setPadding(new Insets(10));
            buttonBox.setAlignment(Pos.CENTER_LEFT);
    
            if (root instanceof Pane) {
                ((Pane) root).getChildren().addAll(buttonBox);
            }
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    private void changeLocale(Locale newLocale, Stage stage, Scene scene) {
        if (!newLocale.equals(locale)) {
            locale = newLocale;
            ResourceBundle newBundle = ResourceBundle.getBundle("auth", locale);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Auth.fxml"), newBundle);
            AuthController authController = new AuthController(tcpClient, locale);
            fxmlLoader.setController(authController);

            try {
                Parent root = fxmlLoader.load();

                
                Button englishButton = new Button("English");
                englishButton.setOnAction(event -> changeLocale(Locale.forLanguageTag("en-UK"), stage, scene));

                
                Button russianButton = new Button("Русский");
                russianButton.setOnAction(event -> changeLocale(new Locale("ru_RU"), stage, scene));

                HBox buttonBox = new HBox(10, englishButton, russianButton);
                buttonBox.setPadding(new Insets(10));
                buttonBox.setAlignment(Pos.CENTER_LEFT);
        
                if (root instanceof Pane) {
                     
                    ((Pane) root).getChildren().addAll(buttonBox);
                }

                scene.setRoot(root);

                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleUpdate(ActionEvent event){
        TextInputDialog toRemove = new TextInputDialog();
        toRemove.setHeaderText(null);
        toRemove.setContentText(bundle.getString("idPrompt"));
        Optional<String> res = toRemove.showAndWait();

        if (!res.isPresent() || res.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("idPrompt"));
            alert.showAndWait();
            return;
        }
        res.ifPresent(id ->
        {Dialog<Person> dialog = new Dialog<>();
            dialog.setTitle(bundle.getString("updateButtonText"));
            dialog.setHeaderText(null);
    
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
    
            TextField nameField = new TextField();
            nameField.setPromptText(bundle.getString("name"));
            TextField xCoordField = new TextField();
            xCoordField.setPromptText(bundle.getString("coordinatеsX"));
            TextField yCoordField = new TextField();
            yCoordField.setPromptText(bundle.getString("coordinatesY"));
            TextField heightField = new TextField();
            heightField.setPromptText(bundle.getString("height"));
            TextField birthdayField = new TextField();
            birthdayField.setPromptText("YYYY-MM-ddTHH:mm:ss");
            ChoiceBox<Color> eyeColorBox = new ChoiceBox<>(FXCollections.observableArrayList(Color.BLUE, Color.BLACK, Color.ORANGE, Color.BROWN, Color.GREEN, Color.WHITE));
            eyeColorBox.getSelectionModel().selectFirst();
            ChoiceBox<Color> hairColorBox = new ChoiceBox<>(FXCollections.observableArrayList(Color.BLUE, Color.BLACK, Color.ORANGE, Color.BROWN, Color.GREEN, Color.WHITE));
            hairColorBox.getSelectionModel().selectFirst();
            TextField xLocField = new TextField();
            xLocField.setPromptText("X" + " " + bundle.getString("location"));
            TextField yLocField = new TextField();
            yLocField.setPromptText("Y" + " " + bundle.getString("location"));
            TextField zLocField = new TextField();
            zLocField.setPromptText("Z " + bundle.getString("location"));
            TextField nameLocField = new TextField();
            nameLocField.setPromptText(bundle.getString("location"));
            
            grid.add(new Label(bundle.getString("name") + ":"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label(bundle.getString("coordinates") + ":"), 0, 1);
            grid.add(xCoordField, 1, 1);
            grid.add(yCoordField, 2, 1);
            grid.add(new Label(bundle.getString("height") + ":"), 0, 2);
            grid.add(heightField, 1, 2);
            grid.add(new Label(bundle.getString("birthday") + ":"), 0, 3);
            grid.add(birthdayField, 1, 3);
            grid.add(new Label(bundle.getString("eyeColor") + ":"), 0, 4);
            grid.add(eyeColorBox, 1, 4);
            grid.add(new Label(bundle.getString("hairColor") + ":"), 0, 5);
            grid.add(hairColorBox, 1, 5);
            grid.add(new Label(bundle.getString("location") + ":"), 0, 6);
            grid.add(xLocField, 1, 6);
            grid.add(yLocField, 2, 6);
            grid.add(zLocField, 3, 6);
            grid.add(nameLocField, 4, 6);
    
            ButtonType addButton = new ButtonType(bundle.getString("updateButtonText"), ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType(bundle.getString("cancel"), ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);
    
    
            dialog.getDialogPane().setContent(grid);
    
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButton) {
                    try {
                        String name = nameField.getText();
                        int xCoord = Integer.parseInt(xCoordField.getText());
                        long yCoord = Long.parseLong(yCoordField.getText());
                        Float height = Float.parseFloat(heightField.getText());
                        LocalDateTime birthday = LocalDateTime.parse(birthdayField.getText());
                        Color eyeColor = eyeColorBox.getValue();
                        Color hairColor = hairColorBox.getValue();
                        int xLoc = Integer.parseInt(xLocField.getText());
                        double yLoc = Double.parseDouble(yLocField.getText());
                        double zLoc = Double.parseDouble(zLocField.getText());
                        String nameLoc = nameLocField.getText();
                        Coordinates coordinates = new Coordinates(xCoord, yCoord);
                        Location location = new Location(xLoc, yLoc, zLoc, nameLoc);
                        return new Person(0 ,name, coordinates, LocalDate.now(), height, birthday, eyeColor, hairColor, location, UserHelper.logged_user.getLogin());
                    } catch (NumberFormatException | DateTimeParseException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid input values. Please enter valid input values.");
                        alert.showAndWait();
                        return null;
                    }
                } else {
                    return null;
                }
            });
    
        Optional<Person> result = dialog.showAndWait();
        result.ifPresent(person -> {
                try {
                    tcpClient.sendRequest(new Request("update", id, person, UserHelper.logged_user));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });}

        );
        

    }

    @FXML
    private void handleVisualizationButton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Map.fxml"));
        ResourceBundle newBundle = ResourceBundle.getBundle("visualization", locale);
        loader.setController(new VisualizationController(tcpClient, locale));
        loader.setResources(newBundle);
        Parent root;
        try {
            root = loader.load();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene nextScene = new Scene(root);
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    @FXML
    private void handleShuffle() {
        try {
            tcpClient.sendRequest(new Request("shuffle", "placeholderArg", null, UserHelper.logged_user));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleReorder() {
        try {
            tcpClient.sendRequest(new Request("reorder", "placeholderArg", null, UserHelper.logged_user));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //removegreater
    @FXML
    private void handleRemoveGreater() {

        TextInputDialog toRemove = new TextInputDialog();
        toRemove.setHeaderText(null);
        toRemove.setContentText(bundle.getString("idPrompt"));
        Optional<String> res = toRemove.showAndWait();

        if (!res.isPresent() || res.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("idPrompt"));
            alert.showAndWait();
            return;
        }

        try {
            tcpClient.sendRequest(new Request("remove_greater", res.get(), null, UserHelper.logged_user));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }    
    
    //Remove
    @FXML
    private void handleRemoveById() {

        TextInputDialog toRemove = new TextInputDialog();
        toRemove.setHeaderText(null);
        toRemove.setContentText(bundle.getString("idPrompt"));
        Optional<String> res = toRemove.showAndWait();

        if (!res.isPresent() || res.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("idPrompt"));
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
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle(bundle.getString("addButtonText"));
        dialog.setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText(bundle.getString("name"));
        TextField xCoordField = new TextField();
        xCoordField.setPromptText(bundle.getString("coordinatеsX"));
        TextField yCoordField = new TextField();
        yCoordField.setPromptText(bundle.getString("coordinatesY"));
        TextField heightField = new TextField();
        heightField.setPromptText(bundle.getString("height"));
        TextField birthdayField = new TextField();
        birthdayField.setPromptText("YYYY-MM-ddTHH:mm:ss");
        ChoiceBox<Color> eyeColorBox = new ChoiceBox<>(FXCollections.observableArrayList(Color.BLUE, Color.BLACK, Color.ORANGE, Color.BROWN, Color.GREEN, Color.WHITE));
        eyeColorBox.getSelectionModel().selectFirst();
        ChoiceBox<Color> hairColorBox = new ChoiceBox<>(FXCollections.observableArrayList(Color.BLUE, Color.BLACK, Color.ORANGE, Color.BROWN, Color.GREEN, Color.WHITE));
        hairColorBox.getSelectionModel().selectFirst();
        TextField xLocField = new TextField();
        xLocField.setPromptText("X" + " " + bundle.getString("location"));
        TextField yLocField = new TextField();
        yLocField.setPromptText("Y" + " " + bundle.getString("location"));
        TextField zLocField = new TextField();
        zLocField.setPromptText("Z " + bundle.getString("location"));
        TextField nameLocField = new TextField();
        nameLocField.setPromptText(bundle.getString("location"));
        
        grid.add(new Label(bundle.getString("name") + ":"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label(bundle.getString("coordinates") + ":"), 0, 1);
        grid.add(xCoordField, 1, 1);
        grid.add(yCoordField, 2, 1);
        grid.add(new Label(bundle.getString("height") + ":"), 0, 2);
        grid.add(heightField, 1, 2);
        grid.add(new Label(bundle.getString("birthday") + ":"), 0, 3);
        grid.add(birthdayField, 1, 3);
        grid.add(new Label(bundle.getString("eyeColor") + ":"), 0, 4);
        grid.add(eyeColorBox, 1, 4);
        grid.add(new Label(bundle.getString("hairColor") + ":"), 0, 5);
        grid.add(hairColorBox, 1, 5);
        grid.add(new Label(bundle.getString("location") + ":"), 0, 6);
        grid.add(xLocField, 1, 6);
        grid.add(yLocField, 2, 6);
        grid.add(zLocField, 3, 6);
        grid.add(nameLocField, 4, 6);

        ButtonType addButton = new ButtonType(bundle.getString("addButtonText"), ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType(bundle.getString("cancel"), ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    String name = nameField.getText();
                    int xCoord = Integer.parseInt(xCoordField.getText());
                    long yCoord = Long.parseLong(yCoordField.getText());
                    Float height = Float.parseFloat(heightField.getText());
                    LocalDateTime birthday = LocalDateTime.parse(birthdayField.getText());
                    Color eyeColor = eyeColorBox.getValue();
                    Color hairColor = hairColorBox.getValue();
                    int xLoc = Integer.parseInt(xLocField.getText());
                    double yLoc = Double.parseDouble(yLocField.getText());
                    double zLoc = Double.parseDouble(zLocField.getText());
                    String nameLoc = nameLocField.getText();
                    Coordinates coordinates = new Coordinates(xCoord, yCoord);
                    Location location = new Location(xLoc, yLoc, zLoc, nameLoc);
                    return new Person(0 ,name, coordinates, LocalDate.now(), height, birthday, eyeColor, hairColor, location, UserHelper.logged_user.getLogin());
                } catch (NumberFormatException | DateTimeParseException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid input values. Please enter valid input values.");
                    alert.showAndWait();
                    return null;
                }
            } else {
                return null;
            }
        });

    Optional<Person> result = dialog.showAndWait();
    result.ifPresent(person -> {
            try {
                tcpClient.sendRequest(new Request("add", "placeholderArg", person, UserHelper.logged_user));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}