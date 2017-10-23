package ru.ifmo.fitp.labtestermaster.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.fitp.labtestermaster.domain.problem.Problem;
import ru.ifmo.fitp.labtestermaster.repository.ProblemRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProblemController {

    private static final Logger LOG = Logger.getLogger(ProblemController.class);

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemController(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @PostMapping(value = "/problem")
    public ResponseEntity problem(@RequestBody Problem[] problems) {
        LOG.info(String.format("Add %s new problems", problems.length));

        problemRepository.save(Arrays.asList(problems));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/problems")
    public ResponseEntity<List<Problem>> problems() {
        LOG.info("New request for getting all names of problems");

        Iterable<Problem> problems = problemRepository.findAll();
        List<Problem> result = new ArrayList<>();
        problems.forEach(result::add);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
