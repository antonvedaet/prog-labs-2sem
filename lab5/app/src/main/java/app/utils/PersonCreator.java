package app.utils;
import app.utils.*;
import app.data.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonCreator {
    Scanner scanner;

    public PersonCreator(){
        scanner = new Scanner(System.in);
    }

    public String nameCreate(){
        String name = "";
        try{
            IOHandler.println("Введите имя: ");
            name = scanner.nextLine();
        }catch (InputMismatchException e){
            IOHandler.println("Неправильный формат введенных данных, попробуйте еще раз.");
            nameCreate();
        }
        return name;
    }
    //WIP
}
