package ru.ifmo.fitp.labtestermaster.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;
import ru.ifmo.fitp.labtestermaster.repository.criteria.SubmitReportCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SubmitReportSpecification implements Specification<SubmitReport> {

    private SubmitReportCriteria criteria;

    public SubmitReportSpecification(SubmitReportCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<SubmitReport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.equal(root.get(criteria.getKey()), criteria.getValue());
    }
}
