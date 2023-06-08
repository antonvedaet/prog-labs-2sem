package ifmo.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DisplayPerson extends Person {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private DisplayCoordinates coordinates; //Поле не может быть null

    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float height; //Поле не может быть null, Значение поля должно быть больше 0
    private LocalDateTime birthday; //Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private DisplayLocation location; //Поле не может быть null
    private String creator;
    private boolean saved;

    private transient IntegerProperty idProperty = new SimpleIntegerProperty();
    private transient StringProperty nameProperty = new SimpleStringProperty();
    private transient ObjectProperty<DisplayCoordinates> coordinatesProperty = new SimpleObjectProperty<>();
    private transient ObjectProperty<LocalDate> creationDateProperty = new SimpleObjectProperty<>();
    private transient FloatProperty heightProperty = new SimpleFloatProperty();
    private transient ObjectProperty<LocalDateTime> birthdayProperty = new SimpleObjectProperty<>();
    private transient ObjectProperty<Color> eyeColorProperty = new SimpleObjectProperty<>();
    private transient ObjectProperty<Color> hairColorProperty = new SimpleObjectProperty<>();
    private transient ObjectProperty<DisplayLocation> locationProperty = new SimpleObjectProperty<>();
    private transient StringProperty creatorProperty = new SimpleStringProperty();
    private transient BooleanProperty savedProperty = new SimpleBooleanProperty();
    
    public DisplayPerson(Person person){
        this.id = person.getId();
        this.name = person.getName();
        this.coordinates = new DisplayCoordinates(person.getCoordinates());
        this.creationDate = person.getCreationDate();
        this.height = person.getHeight();
        this.birthday = person.getBirthday();
        this.eyeColor = person.getEyeColor();
        this.hairColor = person.getHairColor();
        this.location = new DisplayLocation(person.getLocation());
        this.creator = person.getCreator();
        this.saved = false;

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

    public DisplayCoordinates getCoordinates() {
        return coordinates;
    }

    public DisplayLocation geLocation(){
        return location;
    }

    //JAVAFX
    public IntegerProperty getIdProperty() {
        return idProperty;
    }
    public StringProperty getNameProperty() {
        return nameProperty;
    }
    public ObjectProperty<DisplayCoordinates> getCoordinatesProperty() {
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
    public ObjectProperty<DisplayLocation> getLocationProperty() {
        return locationProperty;
    }
    public StringProperty getCreatorProperty() {
        return creatorProperty;
    }
    public BooleanProperty getSavedProperty() {
        return savedProperty;
    }
}
