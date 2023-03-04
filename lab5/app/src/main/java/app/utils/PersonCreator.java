package app.utils;
import app.data.Coordinates;
import app.data.Location;
import app.exceptions.ValueException;
import app.data.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Класс для создания объектов класса Person
 * @see app.data.Person
 * @see java.util.Scanner
 */
public class PersonCreator {
    Scanner scanner;
    public PersonCreator(){
        scanner = new Scanner(System.in);
    }

    public String nameCreate(){
        String name = null;
        try{
            IOHandler.println("Введите имя: ");
            name = scanner.nextLine();
            if(name.trim().isEmpty()) throw new ValueException(); 
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            name = nameCreate();
        }catch (ValueException ve){
            IOHandler.println("Имя не может быть пустым");
            name = nameCreate();
        }
        return name;
    }

    public Coordinates coordinatesCreate(){
        int x=0;
        long y=0;
        Coordinates coordinates = null;
        try{
            IOHandler.println("Координаты:\nВведите долготу: ");
            x = scanner.nextInt();
            IOHandler.println("Введите широту: ");
            y = scanner.nextLong();
            if(x < -72 || y < -647) throw new ValueException();
            coordinates = new Coordinates(x, y);
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            scanner.nextLine();
            coordinates = coordinatesCreate();
        }catch (ValueException ve){
            IOHandler.println("Долгота должна быть больше -72, широта больше -647");
            coordinates = coordinatesCreate();
        }
        return coordinates;
    }

    public Float heightCreate(){
        Float height = null;
        try{
            IOHandler.println("Введите рост: ");
            height = scanner.nextFloat();
            if(height <= 0) throw new ValueException();
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            scanner.nextLine();
            height = heightCreate();
        }catch (ValueException ve){
            IOHandler.println("Рост должен быть больше 0");
            scanner.nextLine();
            height = heightCreate();
        }
        scanner.nextLine();
        return height; 
    }

    public LocalDateTime bdayCreate(){
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String inputTime = "";
        LocalDateTime time = null; 
        try{
            IOHandler.println("Введите дату и время рождения(год-месяц-день час:минуты): ");
            inputTime = scanner.nextLine();
            time = LocalDateTime.parse(inputTime, formatter);
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            time = bdayCreate();
        }catch (java.time.format.DateTimeParseException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            time = bdayCreate();
        }
        return time; 
    }

    public Color eyeColorChoose(){
        Color color = null;
        
        try{
            IOHandler.println("Выберите цвет глаз из предложенных: GREEN, BLACK, WHITE, BROWN");
            color = Color.valueOf(scanner.nextLine().toUpperCase());
        }catch (IllegalArgumentException e){
            IOHandler.println("Нету такого цвета");
            color = eyeColorChoose();
        }
        return color; 
    }

    public Color hairColorChoose(){
        Color color = null;
        
        try{
            IOHandler.println("Выберите цвет волос из предложенных: GREEN, BLACK, BLUE, ORANGE, BROWN");
            color = Color.valueOf(scanner.nextLine().toUpperCase());
        }catch (IllegalArgumentException e){
            IOHandler.println("Нету такого цвета");
            color = eyeColorChoose();
        }
        return color; 
    }

    public Location locationCreate(){
        Integer x = 0;
        Double y = 0D;
        Double z = 0D;
        String name = " ";
        Location location = null;
        try{
            IOHandler.println("Введите координаты и название местоположения:\nВведите долготу: ");
            x = scanner.nextInt();
            IOHandler.println("Введите широту: ");
            y = scanner.nextDouble();
            IOHandler.println("Введите высоту: ");
            z = scanner.nextDouble();
            scanner.nextLine();
            IOHandler.println("Введите название: ");
            name = scanner.nextLine();
            if(name.trim().isEmpty()) throw new ValueException();
            location = new Location(x, y, z, name);
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            scanner.nextLine();
            location = locationCreate();
        }catch (ValueException ve){
            IOHandler.println("Название не может быть пустым");
            location = locationCreate();
        }
        return location; 
    }
}
