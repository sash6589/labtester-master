package ru.ifmo.fitp.labtestermaster.dao.task.language.cpp;

import ru.ifmo.fitp.labtestermaster.dao.task.CommandTaskDAO;

public class CompileCppDAO extends CommandTaskDAO {
    public CompileCppDAO() {
        super("g++ main.cpp -o main");
    }
}
