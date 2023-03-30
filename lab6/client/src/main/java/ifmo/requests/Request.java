package ifmo.requests;

import ifmo.commands.AbstractCommand;
import ifmo.commands.Command;
public class Request {
    private AbstractCommand command;
    private String arguments;

    Request (AbstractCommand command, String arguments){
        this.command = command;
        this.arguments = arguments;
    }
}
