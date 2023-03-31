package ifmo.requests;

import java.io.Serializable;

public class Request implements Serializable {
    private String name;
    private String arguments;

    public Request(String name, String arguments){
        this.name = name;
        this.arguments = arguments;
    }

    public String getCommandName() {
        return name;
    }

    public String getArguments() {
        return arguments;
    }
}
