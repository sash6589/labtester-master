package ru.ifmo.fitp.labtestermaster.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ifmo.fitp.labtestermaster.domain.problem.Problem;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
}
