package ru.ifmo.fitp.labtestermaster.domain.problem;

import javax.persistence.*;

@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean checkCodestyle;

    @Column(columnDefinition = "TEXT")
    private String checkCodestyleCommand;

    private boolean runTests;

    @Column(columnDefinition = "TEXT")
    private String runTestsUrl;

    @Column(columnDefinition = "TEXT")
    private String runTestsCommand;

    private boolean runFileTests;

    @Column(columnDefinition = "TEXT")
    private String runFileTestsUrl;

    private String languages[];

    public Problem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckCodestyle() {
        return checkCodestyle;
    }

    public void setCheckCodestyle(boolean checkCodestyle) {
        this.checkCodestyle = checkCodestyle;
    }

    public String getCheckCodestyleCommand() {
        return checkCodestyleCommand;
    }

    public void setCheckCodestyleCommand(String checkCodestyleCommand) {
        this.checkCodestyleCommand = checkCodestyleCommand;
    }

    public boolean isRunTests() {
        return runTests;
    }

    public void setRunTests(boolean runTests) {
        this.runTests = runTests;
    }

    public String getRunTestsUrl() {
        return runTestsUrl;
    }

    public void setRunTestsUrl(String runTestsUrl) {
        this.runTestsUrl = runTestsUrl;
    }

    public boolean isRunFileTests() {
        return runFileTests;
    }

    public void setRunFileTests(boolean runFileTests) {
        this.runFileTests = runFileTests;
    }

    public String getRunFileTestsUrl() {
        return runFileTestsUrl;
    }

    public void setRunFileTestsUrl(String runFileTestsUrl) {
        this.runFileTestsUrl = runFileTestsUrl;
    }

    public String getRunTestsCommand() {
        return runTestsCommand;
    }

    public void setRunTestsCommand(String runTestsCommand) {
        this.runTestsCommand = runTestsCommand;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }
}
