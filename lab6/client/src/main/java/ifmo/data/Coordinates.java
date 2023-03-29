package ifmo.data;

import java.io.Serializable;

/**
 * Класс который является одним из полей Person
 * @see Person
 */
public class Coordinates  implements Serializable {
    private int x; //Значение поля должно быть больше -72
    private long y; //Значение поля должно быть больше -647
    public Coordinates(int x, long y){
        this.x = x;
        this.y = y;
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
}