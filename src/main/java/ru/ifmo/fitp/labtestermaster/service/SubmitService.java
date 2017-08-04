package ru.ifmo.fitp.labtestermaster.service;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ifmo.fitp.labtestermaster.dao.task.*;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;
import ru.ifmo.fitp.labtestermaster.repository.SubmitReportRepository;

@Service
public class SubmitService {

    private static final Logger LOG = Logger.getLogger(SubmitService.class);

    @Value("${worker.url}")
    private String workerUrl;

    final SubmitReportRepository submitReportRepository;
    final RestTemplate restTemplate;

    @Autowired
    public SubmitService(SubmitReportRepository submitReportRepository, RestTemplateBuilder restTemplateBuilder) {
        this.submitReportRepository = submitReportRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

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

        HttpEntity<TasksDAO> request = new HttpEntity<>(tasks);
        ResponseEntity<SubmitReport> response = restTemplate.exchange(
                workerUrl, HttpMethod.POST, request, SubmitReport.class);

        LOG.info("Response from worker: status code " + response.getStatusCode());

        LOG.info("Save submit report to database");

        return submitReportRepository.save(response.getBody());
    }
}
