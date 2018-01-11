package ru.ifmo.fitp.labtestermaster.domain.report;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
public class SubmitReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @Column(columnDefinition = "TEXT")
    private String username;

    @Column(columnDefinition = "TEXT")
    private String fullname;

    @Column(columnDefinition = "TEXT")
    private String testStdout;

    @Column(columnDefinition = "TEXT")
    private String testStderr;

    @Column(columnDefinition = "TEXT")
    private String codestyleStdout;

    @Column(columnDefinition = "TEXT")
    private String codestyleStderr;

    @Column(columnDefinition = "TEXT")
    private String fileTestsResult;

    private String problemName;

    public SubmitReport() {

    }

    public String getTestStdout() {
        return testStdout;
    }

    public String getTestStderr() {
        return testStderr;
    }

    public String getCodestyleStdout() {
        return codestyleStdout;
    }

    public String getCodestyleStderr() {
        return codestyleStderr;
    }

    public void setCodestyleStdout(String codestyleStdout) {
        this.codestyleStdout = codestyleStdout;
    }

    public void setCodestyleStderr(String codestyleStderr) {
        this.codestyleStderr = codestyleStderr;
    }

    public void setTestStdout(String testStdout) {
        this.testStdout = testStdout;
    }

    public void setTestStderr(String testStderr) {
        this.testStderr = testStderr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFileTestsResult() {
        return fileTestsResult;
    }

    public void setFileTestsResult(String fileTestsResult) {
        this.fileTestsResult = fileTestsResult;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
