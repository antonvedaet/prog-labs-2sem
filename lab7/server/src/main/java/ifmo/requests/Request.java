package ifmo.requests;

import java.io.Serializable;

import ifmo.data.Person;
import ifmo.data.User;

public class Request implements Serializable {
    private String name;
    private String arguments;
    private Person person;
    private User user;

    public Request(String name, String arguments, Person person, User user){
        this.name = name;
        this.arguments = arguments;
        this.person = person;
        this.user = user;
    }

    public String getCommandName() {
        return name;
    }

    public String getArguments() {
        return arguments;
    }

    public Person getPerson() {
        return person;
    }
    public User getUser() {
        return user;
    }
}