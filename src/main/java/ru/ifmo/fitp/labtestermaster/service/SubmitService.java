package ru.ifmo.fitp.labtestermaster.service;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ifmo.fitp.labtestermaster.dao.task.*;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;

@Service
public class SubmitService {

    private static final Logger LOG = Logger.getLogger(SubmitService.class);

    @Value("${worker.url}")
    private String workerUrl;

    public SubmitReport submit(String gitUrl) {

        TasksDAO tasks = getTaskPipeline(gitUrl);

        return makeRequest(tasks);
    }

    @NotNull
    private TasksDAO getTaskPipeline(String gitUrl) {
        LOG.info("Generate task pipeline");

        return  new TasksDAO(new AbstractTaskDAO[]{
                new PrepareEnvironmentDAO(),
                new GitCloneDAO(gitUrl),
                new MoveToDAO(),
                new CheckCodestyleDAO("pep8 run/main.py"),
                new RunTestsDAO("python -m test.tests"),
                new CleanEnvironmentDAO()
        });
    }

    private SubmitReport makeRequest(TasksDAO tasks) {
        LOG.info("Make request to worker");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TasksDAO> request = new HttpEntity<>(tasks);
        ResponseEntity<SubmitReport> response = restTemplate.exchange(
                workerUrl, HttpMethod.POST, request, SubmitReport.class);

        LOG.info("Response from worker: status code " + response.getStatusCode());

        return response.getBody();
    }
}
