package ru.ifmo.fitp.labtestermaster.dao.task;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GitCloneDAO.class, name = "gitClone"),
        @JsonSubTypes.Type(value = GitCloneSolutionDAO.class, name = "gitCloneSolution"),
        @JsonSubTypes.Type(value = GitCloneTestsDAO.class, name = "gitCloneTests"),
        @JsonSubTypes.Type(value = GitCloneFileTestsDAO.class, name = "gitCloneFileTests"),
        @JsonSubTypes.Type(value = CleanEnvironmentDAO.class, name = "cleanEnvironment"),
        @JsonSubTypes.Type(value = SolutionMoveToDAO.class, name = "solutionMoveTo"),
        @JsonSubTypes.Type(value = TestsMoveToDAO.class, name = "testsMoveTo"),
        @JsonSubTypes.Type(value = FileTestsMoveToDAO.class, name = "fileTestsMoveTo"),
        @JsonSubTypes.Type(value = PrepareEnvironmentDAO.class, name = "prepareEnvironment"),
        @JsonSubTypes.Type(value = RunTestsDAO.class, name = "runTests"),
        @JsonSubTypes.Type(value = RunFileTestsDAO.class, name = "runFileTests"),
        @JsonSubTypes.Type(value = CheckCodestyleDAO.class, name = "checkCodestyle")
})
public abstract class AbstractTaskDAO {

}
