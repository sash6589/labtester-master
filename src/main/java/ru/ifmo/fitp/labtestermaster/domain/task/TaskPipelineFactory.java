package ru.ifmo.fitp.labtestermaster.domain.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ifmo.fitp.labtestermaster.dao.solution.SolutionDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.TasksDAO;
import ru.ifmo.fitp.labtestermaster.domain.problem.Problem;
import ru.ifmo.fitp.labtestermaster.repository.ProblemRepository;

import java.util.function.Supplier;

@Component
public class TaskPipelineFactory {

    private static final Logger LOG = Logger.getLogger(TaskPipelineFactory.class);

    private final ProblemRepository problemRepository;

    @Autowired
    public TaskPipelineFactory(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public TasksDAO getTaskPipeline(String problemName, String language, String gitUrl) {
        Tasks tasks = new Tasks();
        Supplier<Void> func = () -> {
            tasks.addBeginCommonTasksWithGit(gitUrl, language);
            return null;
        };
        return getTaskPipeline(problemName, func, tasks);
    }

    public TasksDAO getTaskPipeline(SolutionDAO solutionDAO, String language) {
        Tasks tasks = new Tasks();
        Supplier<Void> func = () -> {
            tasks.addBeginCommonTasksWithProgram(solutionDAO.getProgram(), language);
            return null;
        };
        return getTaskPipeline(solutionDAO.getProblemName(), func, tasks);
    }

    private TasksDAO getTaskPipeline(String problemName, Supplier<Void> func, Tasks tasks) {
        LOG.info("Generate task pipeline for problem " + problemName);

        Problem problem = problemRepository.findProblemByName(problemName);

        func.get();

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
