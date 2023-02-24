package app.utils;
import app.data.Coordinates;
import app.data.Location;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            nameCreate();
        }
        return name;
    }

    public Coordinates coordinatesCreate(){
        int x=0;
        long y=0;
        try{
            IOHandler.println("Координаты:\nВведите долготу: ");
            x = scanner.nextInt();
            IOHandler.println("Введите широту: ");
            y = scanner.nextLong();
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            scanner.nextLine();
            coordinatesCreate();
        }
        return new Coordinates(x, y); 
    }

    public Float heightCreate(){
        Float height = null;
        try{
            IOHandler.println("Введите рост: ");
            height = scanner.nextFloat();
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            scanner.nextLine();
            heightCreate();
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
            bdayCreate();
        }catch (java.time.format.DateTimeParseException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            bdayCreate();
        }
        return time; 
    }

    // public Color eyeColorChoose(){
    //     Color color;
    //     try{
    //         IOHandler.println("Введите дату и время рождения(\"год-месяц-день час:минуты\"): ");
    //     }catch (InputMismatchException e){
    //         IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
    //         eyeColorChoose();
    //     }
    //     return color; 
    // }

    public Location locationCreate(){
        Integer x = 0;
        Double y = 0D;
        Double z = 0D;
        String name = " ";
        try{
            IOHandler.println("Введите координаты и название местоположения:\nВведите долготу: ");
            x = scanner.nextInt();
            IOHandler.println("Введите широту: ");
            y = scanner.nextDouble();
            IOHandler.println("Введите высоту: ");
            z = scanner.nextDouble();
            IOHandler.println("Введите долготу: ");
            name = scanner.nextLine();
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            scanner.nextLine();
            locationCreate();
        }
        return new Location(x, y, z, name); 
    }
    //WIP
}
