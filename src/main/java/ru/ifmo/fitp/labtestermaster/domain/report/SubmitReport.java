package ru.ifmo.fitp.labtestermaster.domain.report;

@SuppressWarnings("unused")
public class SubmitReport {
    private String testStdout;
    private String testStderr;
    private String codestyleStdout;
    private String codestyleStderr;

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
}
