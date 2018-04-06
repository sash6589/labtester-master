package ru.ifmo.fitp.labtestermaster.dao.task.language;

import ru.ifmo.fitp.labtestermaster.dao.task.CommandTaskDAO;

public class BuildDAO extends CommandTaskDAO {
    public BuildDAO(String command) {
        super(command);
    }

    public BuildDAO(String... command) {
        super(command);
    }
}
