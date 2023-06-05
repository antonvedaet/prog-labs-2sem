package ifmo.data;

import java.io.Serializable;
import javafx.beans.property.*;
/**
 * Класс который является одним из полей Person
 * @see Person
 */
public class Coordinates  implements Serializable {
    private int x; //Значение поля должно быть больше -72
    private long y; //Значение поля должно быть больше -647

    private IntegerProperty xProperty = new SimpleIntegerProperty();
    private LongProperty yProperty = new SimpleLongProperty();

    public Coordinates(int x, long y){
        this.x = x;
        this.y = y;

        xProperty.bindBidirectional(new SimpleIntegerProperty(x));
        yProperty.bindBidirectional(new SimpleLongProperty(y));
    }
    public Coordinates(){}

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public long getY() {
        return y;
    }
    public void setY(long y) {
        this.y = y;
    }

    
    public void bindProperties(){
        xProperty.bindBidirectional(new SimpleIntegerProperty(x));
        yProperty.bindBidirectional(new SimpleLongProperty(y));
    }
    
    public IntegerProperty getXProperty() {
        return xProperty;
    }
    public LongProperty getYProperty() {
        return yProperty;
    }
}