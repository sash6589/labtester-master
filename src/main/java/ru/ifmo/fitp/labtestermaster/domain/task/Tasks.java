package ru.ifmo.fitp.labtestermaster.domain.task;

import ru.ifmo.fitp.labtestermaster.dao.task.*;
import ru.ifmo.fitp.labtestermaster.dao.task.codestyle.CheckCodestyleDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.git.GitCloneFileTestsDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.git.GitCloneSolutionDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.git.GitCloneTestsDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.language.cpp.CompileCppDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.os.*;
import ru.ifmo.fitp.labtestermaster.dao.task.test.RunFileTestsDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.test.RunTestsDAO;

import java.util.ArrayList;
import java.util.List;

public class Tasks {

    private final List<AbstractTaskDAO> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public void addBeginCommonTasksWithGit(String gitUrl, String language) {
        addBeginCommonTasks(new GitCloneSolutionDAO(gitUrl), getBuildLanguageSpecificTask(language));
    }

    public void addBeginCommonTasksWithProgram(String program, String language) {
        addBeginCommonTasks(new SaveSolutionDAO(program), getCompileLanguageSpecificTask(language));
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

    private void addBeginCommonTasks(AbstractTaskDAO task, AbstractTaskDAO languageTask) {
        tasks.add(new PrepareEnvironmentDAO());
        tasks.add(task);
        if (languageTask != null) {
            tasks.add(languageTask);
        }
        tasks.add(new SolutionMoveToDAO());
    }

    private AbstractTaskDAO getCompileLanguageSpecificTask(String language) {
        System.out.println(language);
        switch (language) {
            case "cpp":
                return new CompileCppDAO();
            default:
                return null;
        }
    }

    private AbstractTaskDAO getBuildLanguageSpecificTask(String language) {
        switch (language) {
            default:
                return null;
        }
    }
}
