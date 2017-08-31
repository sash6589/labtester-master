package ru.ifmo.fitp.labtestermaster.domain.task;

import ru.ifmo.fitp.labtestermaster.dao.task.*;

import java.util.ArrayList;
import java.util.List;

public class Tasks {

    private final List<AbstractTaskDAO> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public void addBeginCommonTasks(String gitUrl) {
        tasks.add(new PrepareEnvironmentDAO());
        tasks.add(new GitCloneSolutionDAO(gitUrl));
        tasks.add(new SolutionMoveToDAO());
    }

    public void addCheckCodestyleTask(String command) {
        tasks.add(new CheckCodestyleDAO(command));
    }

    public void addTestsTasks(String gitUrl, String command) {
        tasks.add(new GitCloneTestsDAO(gitUrl));
        tasks.add(new TestsMoveToDAO());
        tasks.add(new RunTestsDAO(command));
    }

    public void addFileTestsTasks(String gitUrl, String command) {
        tasks.add(new GitCloneFileTestsDAO(gitUrl));
        tasks.add(new FileTestsMoveToDAO());
        tasks.add(new RunFileTestsDAO(command));
    }

    public void addEndCommonTasks() {
        tasks.add(new CleanEnvironmentDAO());
    }

    public TasksDAO toTasksDAO() {
        AbstractTaskDAO[] tasksDAO = new AbstractTaskDAO[tasks.size()];
        return new TasksDAO(tasks.toArray(tasksDAO));
    }
}
