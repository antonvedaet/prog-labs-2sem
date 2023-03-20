package app.commands;
public abstract class AbstractCommand implements Command {
    private String name;
    private String description;
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
