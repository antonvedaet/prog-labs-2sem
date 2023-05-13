package ifmo.utils;

import java.io.IOException;
import java.util.Scanner;

import ifmo.data.User;
import ifmo.network.TCPClient;
import ifmo.requests.Request;

public class UserHelper {
    TCPClient client;
    public User user;
    
    public UserHelper(TCPClient client){
        this.client = client;
    }

    public String sendRegister(User user){
        try {
            return client.sendRequest(new Request("register", "placeholderArg", null, user));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Ошибка при получении ответа";
    }

    public String sendLogin(User user){
        try {
          return  client.sendRequest(new Request("login", "placeholderArg", null, user));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Ошибка при получении ответа";
    }

    public User createUser(Scanner scanner){
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String pwd = scanner.nextLine();
        if(login.trim().isBlank()){
            IOHandler.println("Логин не может быть пустой строкой");
            return createUser(scanner);
        }
        if(pwd.trim().isBlank()){
            IOHandler.println("Пароль не может быть пустой строкой");
            return createUser(scanner);
        }
        return new User(login, pwd);
    }

    public boolean ask(Scanner scanner){
        IOHandler.println("Войти/Зарегистрироваться[l/r]");
        String input = scanner.nextLine();
        if(input.equals("r")){
            user = createUser(scanner);
            String reg = sendRegister(user);
            IOHandler.println(reg);
            if(reg.trim().equals("Пользователь успешно зарегестрирован!".trim())){
                return true;
            } else {
                ask(scanner);
            }
        }
        if(input.equals("l")){
            user = createUser(scanner);
            String log = sendLogin(user);
            IOHandler.println(log);
            if(log.trim().equals("Теперь вам доступны комманды, используйте help для их просмотра".trim())){
                return true;
            } else {
                ask(scanner);
            }
        }      
        return ask(scanner); 
    }
}
