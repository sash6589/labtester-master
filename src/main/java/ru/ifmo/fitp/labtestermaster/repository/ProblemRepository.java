package ru.ifmo.fitp.labtestermaster.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ifmo.fitp.labtestermaster.domain.problem.Problem;

import java.util.List;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
    Problem findProblemByName(String name);

    @Query("SELECT name FROM Problem")
    List<String> findNames();
}
