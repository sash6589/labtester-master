package ru.ifmo.fitp.labtestermaster.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.ifmo.fitp.labtestermaster.dao.solution.SolutionDAO;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;
import ru.ifmo.fitp.labtestermaster.service.SubmitService;

@RestController
public class SubmitController {

    private static final Logger LOG = Logger.getLogger(SubmitController.class);

    private final SubmitService submitService;

    @Autowired
    public SubmitController(SubmitService submitService) {
        this.submitService = submitService;
    }

    @GetMapping(value = "/submit")
    public ResponseEntity<SubmitReport> submit(@RequestParam String username,
                                               @RequestParam String problemName,
                                               @RequestParam String language,
                                               @RequestParam String gitUrl) {
        LOG.info(String.format("Submit request for problem %s on %s language from user %s ",
                problemName, language, username));

        SubmitReport submitReport = submitService.submit(username, problemName, language, gitUrl);

        return new ResponseEntity<>(submitReport, HttpStatus.OK);
    }

    @PostMapping(value = "/submit")
    public ResponseEntity<SubmitReport> submit(@RequestParam String username,
                                               @RequestParam String language,
                                               @RequestBody SolutionDAO solutionDAO) {
        LOG.info(String.format("Submit request for problem %s on %s language from user %s ",
                solutionDAO.getProblemName(), language, username));

        SubmitReport submitReport = submitService.submit(username, language, solutionDAO);

        return new ResponseEntity<>(submitReport, HttpStatus.OK);
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "Failed to check solution")
    @ExceptionHandler(HttpClientErrorException.class)
    public void badRequest(HttpClientErrorException e) {
        LOG.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void error(Exception e) throws Exception {
        LOG.warn(e.getMessage());
        throw e;
    }
}
