package ru.ifmo.fitp.labtestermaster.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;
import ru.ifmo.fitp.labtestermaster.repository.SubmitReportRepository;
import ru.ifmo.fitp.labtestermaster.repository.specification.builder.SubmitReportSpecificationBuilder;

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
    public ResponseEntity<List<SubmitReport>> reports(@RequestParam(value = "problemName", required = false) String problemName,
                                                      @RequestParam(value = "username", required = false) String username) {

        LOG.info("New reports request");

        Specification<SubmitReport> spec = buildSpecification(problemName, username);

        Iterable<SubmitReport> all = (spec != null ? reportRepository.findAll(spec) : reportRepository.findAll());

        List<SubmitReport> reports = new ArrayList<>();
        all.forEach(reports::add);

        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    private Specification<SubmitReport> buildSpecification(String problemName, String username) {
        SubmitReportSpecificationBuilder builder = new SubmitReportSpecificationBuilder();
        builder.with("problemName", problemName).with("username", username);
        return builder.build();
    }
}
