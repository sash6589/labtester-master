package ru.ifmo.fitp.labtestermaster.dao.task;

public class TasksDAO {

    private AbstractTaskDAO tasks[];

    public TasksDAO() {

    }

    public TasksDAO(AbstractTaskDAO[] tasks) {
        this.tasks = tasks;
    }

    public AbstractTaskDAO[] getTasks() {
        return tasks;
    }

    public void setTasks(AbstractTaskDAO[] tasks) {
        this.tasks = tasks;
    }
}
