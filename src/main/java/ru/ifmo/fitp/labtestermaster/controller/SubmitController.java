package ru.ifmo.fitp.labtestermaster.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public ResponseEntity<SubmitReport> submit(@RequestParam String gitUrl) {
        LOG.info("New submit request");

        SubmitReport submitReport = submitService.submit(gitUrl);

        return new ResponseEntity<>(submitReport, HttpStatus.OK);
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "Failed to check solution")
    @ExceptionHandler(HttpClientErrorException.class)
    public void badRequest() {

    }
}
