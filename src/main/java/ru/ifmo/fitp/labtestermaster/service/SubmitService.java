package ru.ifmo.fitp.labtestermaster.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ifmo.fitp.labtestermaster.dao.solution.SolutionDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.*;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;
import ru.ifmo.fitp.labtestermaster.domain.task.TaskPipelineFactory;

@Service
public class SubmitService {

    private static final Logger LOG = Logger.getLogger(SubmitService.class);

    @Value("${worker.url}")
    private String workerUrl;

    private final AsyncDBService asyncDBService;
    private final RestTemplate restTemplate;
    private final TaskPipelineFactory taskPipelineFactory;

    @Autowired
    public SubmitService(AsyncDBService asyncDBService, RestTemplateBuilder restTemplateBuilder,
                         TaskPipelineFactory taskPipelineFactory) {
        this.asyncDBService = asyncDBService;
        this.restTemplate = restTemplateBuilder.build();
        this.taskPipelineFactory = taskPipelineFactory;
    }

    public SubmitReport submit(String username, String fullname, String problemName, String language, String gitUrl) {
        TasksDAO tasks = taskPipelineFactory.getTaskPipeline(problemName, language, gitUrl);

        return submit(username, fullname, tasks, problemName, null);
    }

    public SubmitReport submit(String username, String fullname, String language, SolutionDAO solutionDAO) {
        TasksDAO tasks = taskPipelineFactory.getTaskPipeline(solutionDAO, language);

        return submit(username, fullname, tasks, solutionDAO.getProblemName(), solutionDAO.getProgram());
    }

    private SubmitReport submit(String username, String fullname, TasksDAO tasks, String problemName, String solution) {
        SubmitReport report = makeRequest(tasks);
        report.setProblemName(problemName);
        report.setUsername(username);
        report.setFullname(fullname);
        report.setSolution(solution);

        asyncDBService.saveSubmitReport(report);

        return report;
    }

    private SubmitReport makeRequest(TasksDAO tasks) {
        LOG.info("Make request to worker");

        HttpEntity<TasksDAO> request = new HttpEntity<>(tasks);
        ResponseEntity<SubmitReport> response = restTemplate.exchange(
                workerUrl, HttpMethod.POST, request, SubmitReport.class);

        LOG.info("Response from worker: status code " + response.getStatusCodeValue());

        return response.getBody();
    }
}
