package ru.ifmo.fitp.labtestermaster.dao.task.language.cpp;

import ru.ifmo.fitp.labtestermaster.dao.task.CommandTaskDAO;

public class BuildCppDAO extends CommandTaskDAO {
    public BuildCppDAO(String command) {
        super(command);
    }

    public BuildCppDAO(String... command) {
        super(command);
    }
}
