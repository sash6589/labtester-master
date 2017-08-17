package ru.ifmo.fitp.labtestermaster.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;
import ru.ifmo.fitp.labtestermaster.repository.SubmitReportRepository;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ReportsController {

    private static final Logger LOG = Logger.getLogger(ReportsController.class);

    private final SubmitReportRepository reportRepository;

    @Autowired
    public ReportsController(SubmitReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping(value = "/reports")
    public ResponseEntity<List<SubmitReport>> reports() {
        LOG.info("New reports request");

        List<SubmitReport> reports = new ArrayList<>();
        Iterable<SubmitReport> all = reportRepository.findAll();
        all.forEach(reports::add);

        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}
