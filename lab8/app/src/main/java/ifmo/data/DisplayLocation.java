package ifmo.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DisplayLocation {
    private Integer x; //Поле не может быть null
    private Double y; //Поле не может быть null
    private Double z;
    private String name; //Длина строки не должна быть больше 869, Поле может быть null


    private transient StringProperty nameProperty = new SimpleStringProperty();
    
    public DisplayLocation(Location location){
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.name = location.getName();

        nameProperty = new SimpleStringProperty(name);
    }

    public void bindProperties(){
        nameProperty.bindBidirectional(new SimpleStringProperty(name));
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }
}
