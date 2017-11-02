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
import ru.ifmo.fitp.labtestermaster.domain.task.TaskFactory;

@Service
public class SubmitService {

    private static final Logger LOG = Logger.getLogger(SubmitService.class);

    @Value("${worker.url}")
    private String workerUrl;

    private final AsyncDBService asyncDBService;
    private final RestTemplate restTemplate;
    private final TaskFactory taskFactory;

    @Autowired
    public SubmitService(AsyncDBService asyncDBService, RestTemplateBuilder restTemplateBuilder,
                         TaskFactory taskFactory) {
        this.asyncDBService = asyncDBService;
        this.restTemplate = restTemplateBuilder.build();
        this.taskFactory = taskFactory;
    }

    public SubmitReport submit(String username, String problemName, String language, String gitUrl) {
        TasksDAO tasks = taskFactory.getTaskPipeline(problemName, language, gitUrl);

        return submit(username, tasks, problemName);
    }

    public SubmitReport submit(String username, String language, SolutionDAO solutionDAO) {
        TasksDAO tasks = taskFactory.getTaskPipeline(solutionDAO, language);

        return submit(username, tasks, solutionDAO.getProblemName());
    }

    private SubmitReport submit(String username, TasksDAO tasks, String problemName) {
        SubmitReport report = makeRequest(tasks);
        report.setProblemName(problemName);
        report.setUsername(username);

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
