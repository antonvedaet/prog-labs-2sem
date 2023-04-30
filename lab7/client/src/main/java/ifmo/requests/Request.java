package ifmo.requests;

import java.io.Serializable;

import ifmo.data.Person;

public class Request implements Serializable {
    private String name;
    private String arguments;
    private Person person;
    public Request(String name, String arguments, Person person){
        this.name = name;
        this.arguments = arguments;
        this.person = person;
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
}
