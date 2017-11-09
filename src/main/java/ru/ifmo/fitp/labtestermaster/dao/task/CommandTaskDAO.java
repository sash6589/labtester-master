package ru.ifmo.fitp.labtestermaster.dao.task;

import java.util.Arrays;
import java.util.List;

public abstract class CommandTaskDAO extends AbstractTaskDAO {
    private List<String> command;

    public CommandTaskDAO(String command) {
        this.command = Arrays.asList(command.split(" "));
    }

    public CommandTaskDAO(String... command) {
        this.command = Arrays.asList(command);
    }

    public CommandTaskDAO() {
    }

    public List<String> getCommand() {
        return command;
    }
}
