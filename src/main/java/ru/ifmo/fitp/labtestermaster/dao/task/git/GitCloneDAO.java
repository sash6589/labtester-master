package ru.ifmo.fitp.labtestermaster.dao.task.git;

import ru.ifmo.fitp.labtestermaster.dao.task.AbstractTaskDAO;

public class GitCloneDAO extends AbstractTaskDAO {

    private String gitUrl;

    public GitCloneDAO(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public GitCloneDAO() {
    }

    public String getGitUrl() {
        return gitUrl;
    }
}
