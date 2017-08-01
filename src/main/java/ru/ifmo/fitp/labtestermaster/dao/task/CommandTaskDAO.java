package ru.ifmo.fitp.labtestermaster.dao.task;

public abstract class CommandTaskDAO extends AbstractTaskDAO {
    private String command;

    public CommandTaskDAO(String command) {
        this.command = command;
    }

    public CommandTaskDAO() {
    }

    public String getCommand() {
        return command;
    }
}
