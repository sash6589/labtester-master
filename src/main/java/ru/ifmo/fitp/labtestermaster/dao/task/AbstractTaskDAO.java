package ru.ifmo.fitp.labtestermaster.dao.task;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GitCloneDAO.class, name = "gitClone"),
        @JsonSubTypes.Type(value = CleanEnvironmentDAO.class, name = "cleanEnvironment"),
        @JsonSubTypes.Type(value = MoveToDAO.class, name = "moveTo"),
        @JsonSubTypes.Type(value = PrepareEnvironmentDAO.class, name = "prepareEnvironment"),
        @JsonSubTypes.Type(value = RunTestsDAO.class, name = "runTests"),
        @JsonSubTypes.Type(value = CheckCodestyleDAO.class, name = "checkCodestyle")
})
public abstract class AbstractTaskDAO {

}
