package ifmo;

import ifmo.commands.*;
import ifmo.network.TCPClient;
import ifmo.utils.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        TCPClient client = new TCPClient();
        UserHelper userHelper = new UserHelper(client);

        //TODO : registering + login
        boolean logged_in = false;
        while(!logged_in){
            logged_in = userHelper.ask(scanner);
        }

        while(true){
            IOHandler.print("> ");
            String input = scanner.nextLine();
            IOHandler.print(client.sendRequest(input +" placeholderArg", userHelper.user));
        }
    }
}
