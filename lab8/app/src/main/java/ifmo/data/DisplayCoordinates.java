package ifmo.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class DisplayCoordinates extends Coordinates{
    private int x; //Значение поля должно быть больше -72
    private long y; //Значение поля должно быть больше -647

    private transient IntegerProperty xProperty = new SimpleIntegerProperty();
    private transient LongProperty yProperty = new SimpleLongProperty();

    public DisplayCoordinates(Coordinates coordinates){
        this.x = coordinates.getX();
        this.y = coordinates.getY();

        xProperty.bindBidirectional(new SimpleIntegerProperty(x));
        yProperty.bindBidirectional(new SimpleLongProperty(y));
        
    }

    @Override
    public int getX(){
        return x;
    }

    @Override
    public long getY(){
        return y;
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
