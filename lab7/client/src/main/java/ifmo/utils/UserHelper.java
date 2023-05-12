package ifmo.utils;

import java.io.IOException;
import java.util.Scanner;

import ifmo.data.User;
import ifmo.network.TCPClient;
import ifmo.requests.Request;

public class UserHelper {
    TCPClient client;
    
    public UserHelper(TCPClient client){
        this.client = client;
    }

    public void sendRegister(User user){
        try {
            client.sendRequest(new Request("register", "placeholderArg", null, user));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendLogin(User user){
        try {
            client.sendRequest(new Request("login", "placeholderArg", null, user));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public User createUser(Scanner scanner){
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String pwd = scanner.nextLine();
        return new User(login, pwd);
    }
}
