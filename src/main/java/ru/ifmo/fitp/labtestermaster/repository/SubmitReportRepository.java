package ru.ifmo.fitp.labtestermaster.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;

public interface SubmitReportRepository extends CrudRepository<SubmitReport, Long> {
}
