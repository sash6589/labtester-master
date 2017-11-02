package ru.ifmo.fitp.labtestermaster.domain.task;

import ru.ifmo.fitp.labtestermaster.dao.task.*;
import ru.ifmo.fitp.labtestermaster.dao.task.codestyle.CheckCodestyleDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.git.GitCloneFileTestsDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.git.GitCloneSolutionDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.git.GitCloneTestsDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.os.*;
import ru.ifmo.fitp.labtestermaster.dao.task.test.RunFileTestsDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.test.RunTestsDAO;

import java.util.ArrayList;
import java.util.List;

import static ru.ifmo.fitp.labtestermaster.domain.task.LanguageSpecificFactory.*;

public class Tasks {

    private final List<AbstractTaskDAO> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public void addBeginCommonTasksWithGit(String gitUrl, String language) {
        addBeginCommonTasks(new GitCloneSolutionDAO(gitUrl), getBuildTask(language));
    }

    public void addBeginCommonTasksWithProgram(String program, String language) {
        addBeginCommonTasks(new SaveSolutionDAO(program, getExtension(language)),
                getCompileTask(language));
    }

    public void addCheckCodestyleTask(String command) {
        tasks.add(new CheckCodestyleDAO(command));
    }

    public void addTestsTasks(String gitUrl, String command) {
        tasks.add(new GitCloneTestsDAO(gitUrl));
        tasks.add(new TestsMoveToDAO());
        tasks.add(new RunTestsDAO(command));
    }

    public void addFileTestsTasks(String gitUrl, String language) {
        tasks.add(new GitCloneFileTestsDAO(gitUrl));
        tasks.add(new FileTestsMoveToDAO());
        tasks.add(new RunFileTestsDAO(getRunFileTestsCommand(language)));
    }

    public void addEndCommonTasks() {
        tasks.add(new CleanEnvironmentDAO());
    }

    public TasksDAO toTasksDAO() {
        AbstractTaskDAO[] tasksDAO = new AbstractTaskDAO[tasks.size()];
        return new TasksDAO(tasks.toArray(tasksDAO));
    }

    private void addBeginCommonTasks(AbstractTaskDAO task, AbstractTaskDAO languageTask) {
        tasks.add(new PrepareEnvironmentDAO());
        tasks.add(task);
        if (languageTask != null) {
            tasks.add(languageTask);
        }
        tasks.add(new SolutionMoveToDAO());
    }
}
