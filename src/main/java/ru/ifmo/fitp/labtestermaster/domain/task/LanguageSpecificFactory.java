package ru.ifmo.fitp.labtestermaster.domain.task;

import ru.ifmo.fitp.labtestermaster.dao.task.AbstractTaskDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.language.cpp.CompileCppDAO;

public class LanguageSpecificFactory {
    public static AbstractTaskDAO getCompileLanguageSpecificTask(String language) {
        switch (language) {
            case "cpp":
                return new CompileCppDAO();
            default:
                return null;
        }
    }

    public static AbstractTaskDAO getBuildLanguageSpecificTask(String language) {
        switch (language) {
            default:
                return null;
        }
    }

    public static String getLanguageSpecificExtension(String language) {
        switch (language) {
            case "cpp":
                return "cpp";
            case "python":
                return "py";
        }

        throw new IllegalArgumentException("Unknown language");
    }
}
