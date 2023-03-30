package ifmo.requests;

import ifmo.commands.Help;

public class HelpRequest extends Request{
    HelpRequest(){
        super("help", "placeholderArg");
    }
}
