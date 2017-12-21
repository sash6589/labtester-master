package ru.ifmo.fitp.labtestermaster.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;

import java.util.List;

public interface SubmitReportRepository
        extends CrudRepository<SubmitReport, Long>, JpaSpecificationExecutor<SubmitReport> {

    List<SubmitReport> findByProblemName(String problemName);
}
