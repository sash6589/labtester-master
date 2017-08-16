package ru.ifmo.fitp.labtestermaster.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;
import ru.ifmo.fitp.labtestermaster.repository.SubmitReportRepository;

@Service
public class AsyncDBService {

    private static final Logger LOG = Logger.getLogger(SubmitService.class);

    final SubmitReportRepository submitReportRepository;

    @Autowired
    public AsyncDBService(SubmitReportRepository submitReportRepository) {
        this.submitReportRepository = submitReportRepository;
    }

    @Async
    public void saveSubmitReport(SubmitReport report) {
        LOG.info("Save report to database");
        submitReportRepository.save(report);
    }
}
