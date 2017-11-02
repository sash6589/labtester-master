package ru.ifmo.fitp.labtestermaster.dao.task.os;

import ru.ifmo.fitp.labtestermaster.dao.task.AbstractTaskDAO;

public class SaveSolutionDAO extends AbstractTaskDAO {

    private String program;
    private String extension;

    public SaveSolutionDAO(String program, String extension) {
        this.program = program;
        this.extension = extension;
    }

    public SaveSolutionDAO() {
    }

    public String getProgram() {
        return program;
    }

    public String getExtension() {
        return extension;
    }
}
