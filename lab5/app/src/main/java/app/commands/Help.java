package app.commands;

public class Help extends AbstractCommand {

    public Help(String name, String description) {
        super("help", "вывести справку о всех доступных командах");
    }
    
    @Override
    public boolean execute(String arg){
        //команду help сделай
        //ок?
        return true;
    }
}
