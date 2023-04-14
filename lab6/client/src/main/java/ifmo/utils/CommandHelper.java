package ifmo.utils;


import java.io.Serializable;
import java.util.HashMap;

import ifmo.commands.Command;
import ifmo.commands.Help;
import ifmo.commands.Show;
/**
 * Вспомогательный класс в котором содержиться список пар (название команды, описание команды)
 */
public class CommandHelper implements Serializable {
    public static HashMap<String, String> commandList(){

        HashMap<String, String> map= new HashMap<String, String>();
        map.put("help", "вывести справку о всех доступных командах");
        map.put("add", "добавить новый элемент в коллекцию");
        map.put("clear", "очистить коллекцию");
        map.put("count_less_than_height", "выводит количество элементов, значение поля height которых меньше заданного");
        map.put("execute_script", "считать и исполнить скрипт из указанного файла");
        map.put("exit", "выйти из программы");
        map.put("filter_contains_name", "выводит элементы, значение поля name которых содержит заданную подстроку");
        map.put("group_counting_by_id", "сгруппировывает элементы коллекции по значению поля id, выводит количество элементов в каждой группе");
        map.put("info", "вывести информацию о коллекции");
        map.put("remove_by_id", "удалить элемент коллекции по id");
        map.put("remove_greater", " удаляет из коллекции все элементы, превышающие заданный");
        map.put("reorder", "отсортировать коллекцию в обратном порядке");
        map.put("show", "вывести все элементы коллекции");
        map.put("shuffle", "перемешать коллекцию в случайном порядке");
        map.put("update", "обновить значение элемента по id");
        return map;
    }

    public static HashMap<String, Command> argCheckMap(){

        HashMap<String, Command> map= new HashMap<String, Command>();
        map.put("help", new Help());
        // map.put("add", "добавить новый элемент в коллекцию");
        // map.put("clear", "очистить коллекцию");
        // map.put("count_less_than_height", "выводит количество элементов, значение поля height которых меньше заданного");
        // map.put("execute_script", "считать и исполнить скрипт из указанного файла");
        // map.put("exit", "выйти из программы");
        // map.put("filter_contains_name", "выводит элементы, значение поля name которых содержит заданную подстроку");
        // map.put("group_counting_by_id", "сгруппировывает элементы коллекции по значению поля id, выводит количество элементов в каждой группе");
        // map.put("info", "вывести информацию о коллекции");
        // map.put("remove_by_id", "удалить элемент коллекции по id");
        // map.put("remove_greater", " удаляет из коллекции все элементы, превышающие заданный");
        // map.put("reorder", "отсортировать коллекцию в обратном порядке");
        map.put("show", new Show());
        // map.put("shuffle", "перемешать коллекцию в случайном порядке");
        // map.put("update", "обновить значение элемента по id");
        return map;
    }
}