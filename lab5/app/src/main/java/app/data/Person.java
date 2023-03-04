package app.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Хранимый в коллекции класс
 * @see app.utils.CollectionHandler
 */
public class Person {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float height; //Поле не может быть null, Значение поля должно быть больше 0
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(int id, String name, Coordinates coordinates, LocalDate creationDate, Float height,
            LocalDateTime birthday, Color eyeColor, Color hairColor, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
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
    public java.time.LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(java.time.LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(Float height) {
        this.height = height;
    }
    public java.time.LocalDateTime getBirthday() {
        return birthday;
    }
    public void setBirthday(java.time.LocalDateTime birthday) {
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
}