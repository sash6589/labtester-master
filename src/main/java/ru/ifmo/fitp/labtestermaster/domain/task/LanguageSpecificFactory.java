package ru.ifmo.fitp.labtestermaster.domain.task;

import ru.ifmo.fitp.labtestermaster.dao.task.AbstractTaskDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.language.cpp.BuildCppDAO;

public class LanguageSpecificFactory {
    public static AbstractTaskDAO getCompileTask(String language) {
        switch (language) {
            case "cpp":
                return new BuildCppDAO("g++ main.cpp -o main");
            case "c":
                return new BuildCppDAO("gcc main.c -o main");
            default:
                return null;
        }
    }

    public static AbstractTaskDAO getBuildTask(String language) {
        switch (language) {
            case "cpp":
                return new BuildCppDAO("bash", "-c", "cmake ./ && make");
            default:
                return null;
        }
    }

    public static String getExtension(String language) {
        switch (language) {
            case "cpp":
                return "cpp";
            case "c":
                return "c";
            case "python":
                return "py";
        }

        throw new IllegalArgumentException("Unknown language");
    }

    public static String getRunFileTestsCommand(String language) {
        switch (language) {
            case "cpp":
                return "./main";
            case "c":
                return "./main";
            case "python":
                return "python main.py";
        }

        throw new IllegalArgumentException("Unknown language");
    }
}
