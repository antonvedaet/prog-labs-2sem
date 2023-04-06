package ifmo.utils;

import ifmo.network.TCPServer;

/**
 * Вспомогательный класс с методами для вывода в терминал
 */
public class IOHandler {


    public static void println(Object o){
        System.out.println(o);
    }
    public static void print(Object o){
        System.out.print(o);
    }

    

    public static void serverMsg(Object o) {
        System.out.println(o);
    }
}
