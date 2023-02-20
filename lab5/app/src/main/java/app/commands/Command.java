package app.commands;

interface Command {
    public String getName();
    public String getDescription();
    public boolean argCheck(String arg);
    public void execute(String arg);
}
