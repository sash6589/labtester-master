package ru.ifmo.fitp.labtestermaster.domain.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ifmo.fitp.labtestermaster.dao.task.TasksDAO;
import ru.ifmo.fitp.labtestermaster.domain.problem.Problem;
import ru.ifmo.fitp.labtestermaster.repository.ProblemRepository;

@Component
public class TaskFactory {

    private static final Logger LOG = Logger.getLogger(TaskFactory.class);

    private final ProblemRepository problemRepository;

    @Autowired
    public TaskFactory(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public TasksDAO getTaskPipeline(String problemName, String gitUrl) {
        LOG.info("Generate task pipeline for problem " + problemName);

        Problem problem = problemRepository.findProblemByName(problemName);

        Tasks tasks = new Tasks();
        tasks.addBeginCommonTasks(gitUrl);
        if (problem.isCheckCodestyle()) {
            tasks.addCheckCodestyleTask(problem.getCheckCodestyleCommand());
        }
        if (problem.isRunTests()) {
            tasks.addTestsTasks(problem.getRunTestsUrl(), problem.getRunTestsCommand());
        }
        if (problem.isRunFileTests()) {
            tasks.addFileTestsTasks(problem.getRunFileTestsUrl(), problem.getRunFileTestsCommand());
        }
        tasks.addEndCommonTasks();

        return tasks.toTasksDAO();
    }
}
