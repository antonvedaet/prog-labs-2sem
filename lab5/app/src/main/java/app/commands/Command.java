package app.commands;

interface Command {
    public String getName();
    public String getDescription();
    /**
     * Проверка количества и типа аргументов
     * @param arg
     */
    public boolean argCheck(String arg);

    /**
     * Выполнение комманды
     * @param arg
     */
    public void execute(String arg);
}
