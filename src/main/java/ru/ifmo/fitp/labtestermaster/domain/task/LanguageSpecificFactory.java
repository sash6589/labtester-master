package ru.ifmo.fitp.labtestermaster.domain.task;

import ru.ifmo.fitp.labtestermaster.dao.task.AbstractTaskDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.language.cpp.CompileCppDAO;

public class LanguageSpecificFactory {
    public static AbstractTaskDAO getCompileTask(String language) {
        switch (language) {
            case "cpp":
                return new CompileCppDAO();
            default:
                return null;
        }
    }

    public static AbstractTaskDAO getBuildTask(String language) {
        switch (language) {
            default:
                return null;
        }
    }

    public static String getExtension(String language) {
        switch (language) {
            case "cpp":
                return "cpp";
            case "python":
                return "py";
        }

        throw new IllegalArgumentException("Unknown language");
    }

    public static String getRunFileTestsCommand(String language) {
        switch (language) {
            case "cpp":
                return "./main";
            case "python":
                return "python main.py";
        }

        throw new IllegalArgumentException("Unknown language");
    }
}
