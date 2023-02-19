package app.commands;

interface Command {
    public String getName();
    public String getDescription();
    public boolean execute(String arg);
}
