package ru.ifmo.fitp.labtestermaster.repository.specification.builder;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import ru.ifmo.fitp.labtestermaster.domain.report.SubmitReport;
import ru.ifmo.fitp.labtestermaster.repository.criteria.SubmitReportCriteria;
import ru.ifmo.fitp.labtestermaster.repository.specification.SubmitReportSpecification;

import java.util.ArrayList;
import java.util.List;

public class SubmitReportSpecificationBuilder {

    private final List<SubmitReportCriteria> params;

    public SubmitReportSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public SubmitReportSpecificationBuilder with(String key, String value) {
        if (value != null) {
            params.add(new SubmitReportCriteria(key, value));
        }
        return this;
    }

    public Specification<SubmitReport> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<SubmitReport>> specs = new ArrayList<>();
        for (SubmitReportCriteria param: params) {
            specs.add(new SubmitReportSpecification(param));
        }

        Specification<SubmitReport> result = specs.get(0);
        for (int i = 1; i < specs.size(); ++i) {
            result = Specifications.where(result).and(specs.get(i));
        }

        return result;
    }
}
