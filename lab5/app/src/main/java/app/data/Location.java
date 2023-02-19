package app.data;

public class Location {
    private Integer x; //Поле не может быть null
    private Double y; //Поле не может быть null
    private Double z;
    private String name; //Длина строки не должна быть больше 869, Поле может быть null

    public Location(Integer x, Double y, Double z, String name){
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}