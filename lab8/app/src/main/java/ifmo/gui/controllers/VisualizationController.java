package ifmo.gui.controllers;

import java.util.LinkedList;

import ifmo.data.DisplayPerson;
import ifmo.network.TCPClient;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VisualizationController {

    @FXML private Pane rootPane;
    @FXML private ImageView mapView;

    private static final double MAP_WIDTH = 800;
    private static final double MAP_HEIGHT = 600;
    private static final double MAP_LATITUDE = 39.9042;
    private static final double MAP_LONGITUDE = 116.4074;
    private static final int MAP_ZOOM = 10;

    TCPClient tcpClient;
    LinkedList<DisplayPerson> persons;

    VisualizationController(TCPClient tcpClient, LinkedList<DisplayPerson> persons){
        this.tcpClient = tcpClient;
        this.persons = persons;
    }
    
    @FXML
    public void initialize() {
    Image mapImage = new Image(
            String.format("https://www.openstreetmap.org/export/"+
                    "staticmap?center=%f,%f&zoom=%d&size=%dx%d&maptype=mapnik",
                    MAP_LATITUDE, MAP_LONGITUDE, MAP_ZOOM,
                    (int) MAP_WIDTH, (int) MAP_HEIGHT));
    mapView.setImage(mapImage);
    }

    private void addDotsToMap() {
        for (DisplayPerson person : persons) {
            double[] pixelCoords = convertToPixelCoords((double) person.getCoordinates().getX(), (double) person.getCoordinates().getY());
            Circle circle = new Circle(pixelCoords[0], pixelCoords[1], 5, Color.RED);
            rootPane.getChildren().add(circle);
        }
    }

    private double[] convertToPixelCoords(double latitude, double longitude) {
        double x = ((longitude + 180.0) / 360.0) * MAP_WIDTH;
        double y = ((1.0 - Math.log(Math.tan(Math.toRadians(latitude)) +
                1.0 / Math.cos(Math.toRadians(latitude))) / Math.PI) / 2.0) * MAP_HEIGHT;
        return new double[] {x, y};
    }

    public void setPeopleList(LinkedList<DisplayPerson> people) {
        this.persons = people;
        addDotsToMap();
    }

}
