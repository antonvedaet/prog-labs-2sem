package ifmo.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.*;
/**
 * Хранимый в коллекции класс
 * @see ifmo.utils.CollectionHandler
 */
public class Person implements Serializable{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float height; //Поле не может быть null, Значение поля должно быть больше 0
    private LocalDateTime birthday; //Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Location location; //Поле не может быть null
    private String creator;
    private boolean saved;

    private IntegerProperty idProperty = new SimpleIntegerProperty();
    private StringProperty nameProperty = new SimpleStringProperty();
    private ObjectProperty<Coordinates> coordinatesProperty = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> creationDateProperty = new SimpleObjectProperty<>();
    private FloatProperty heightProperty = new SimpleFloatProperty();
    private ObjectProperty<LocalDateTime> birthdayProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Color> eyeColorProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Color> hairColorProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Location> locationProperty = new SimpleObjectProperty<>();
    private StringProperty creatorProperty = new SimpleStringProperty();
    private BooleanProperty savedProperty = new SimpleBooleanProperty();

    public Person(int id, String name, Coordinates coordinates, LocalDate creationDate, Float height,
            LocalDateTime birthday, Color eyeColor, Color hairColor, Location location, String creator) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
        this.creator = creator;
        this.saved = false;

        idProperty.bindBidirectional(new SimpleIntegerProperty(id));
        nameProperty.bindBidirectional(new SimpleStringProperty(name));
        coordinatesProperty.bindBidirectional(new SimpleObjectProperty<>(coordinates));
        creationDateProperty.bindBidirectional(new SimpleObjectProperty<>(creationDate));
        heightProperty.bindBidirectional(new SimpleFloatProperty(height));
        birthdayProperty.bindBidirectional(new SimpleObjectProperty<>(birthday));
        eyeColorProperty.bindBidirectional(new SimpleObjectProperty<>(eyeColor));
        hairColorProperty.bindBidirectional(new SimpleObjectProperty<>(hairColor));
        locationProperty.bindBidirectional(new SimpleObjectProperty<>(location));
        creatorProperty.bindBidirectional(new SimpleStringProperty(creator));
        savedProperty.bindBidirectional(new SimpleBooleanProperty(saved));
    }

    public Person(){}
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(Float height) {
        this.height = height;
    }
    public LocalDateTime getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
    public Color getEyeColor() {
        return eyeColor;
    }
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }
    public Color getHairColor() {
        return hairColor;
    }
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setCreator(String creator){
        this.creator = creator;
    }
    public String getCreator(){
        return creator;
    }
    public void setSaved(){
        this.saved = true;
    }
    public boolean getSaved(){
        return saved;
    }

    public void bindProperties(){

        coordinates.bindProperties();
        location.bindProperties();
        idProperty.bindBidirectional(new SimpleIntegerProperty(id));
        nameProperty.bindBidirectional(new SimpleStringProperty(name));
        coordinatesProperty.bindBidirectional(new SimpleObjectProperty<>(coordinates));
        creationDateProperty.bindBidirectional(new SimpleObjectProperty<>(creationDate));
        heightProperty.bindBidirectional(new SimpleFloatProperty(height));
        birthdayProperty.bindBidirectional(new SimpleObjectProperty<>(birthday));
        eyeColorProperty.bindBidirectional(new SimpleObjectProperty<>(eyeColor));
        hairColorProperty.bindBidirectional(new SimpleObjectProperty<>(hairColor));
        locationProperty.bindBidirectional(new SimpleObjectProperty<>(location));
        creatorProperty.bindBidirectional(new SimpleStringProperty(creator));
        savedProperty.bindBidirectional(new SimpleBooleanProperty(saved));
    }

    //JAVAFX
    public IntegerProperty getIdProperty() {
        return idProperty;
    }
    public StringProperty getNameProperty() {
        return nameProperty;
    }
    public ObjectProperty<Coordinates> getCoordinatesProperty() {
        return coordinatesProperty;
    }
    public ObjectProperty<LocalDate> getCreationDateProperty() {
        return creationDateProperty;
    }
    public FloatProperty getHeightProperty() {
        return heightProperty;
    }
    public ObjectProperty<LocalDateTime> getBirthdayProperty() {
        return birthdayProperty;
    }
    public ObjectProperty<Color> getEyeColorProperty() {
        return eyeColorProperty;
    }
    public ObjectProperty<Color> getHairColorProperty() {
        return hairColorProperty;
    }
    public ObjectProperty<Location> getLocationProperty() {
        return locationProperty;
    }
    public StringProperty getCreatorProperty() {
        return creatorProperty;
    }
    public BooleanProperty getSavedProperty() {
        return savedProperty;
    }
}