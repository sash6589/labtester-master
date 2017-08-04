package ru.ifmo.fitp.labtestermaster.domain.report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
public class SubmitReport {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
