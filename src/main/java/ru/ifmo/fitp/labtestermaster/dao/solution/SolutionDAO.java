package ru.ifmo.fitp.labtestermaster.dao.solution;

public class SolutionDAO {

    private String problemName;
    private String program;

    public SolutionDAO() {

    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
