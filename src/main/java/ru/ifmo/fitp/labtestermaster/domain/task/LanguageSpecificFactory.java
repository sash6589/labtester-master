package ru.ifmo.fitp.labtestermaster.domain.task;

import ru.ifmo.fitp.labtestermaster.dao.task.AbstractTaskDAO;
import ru.ifmo.fitp.labtestermaster.dao.task.language.BuildDAO;

class LanguageSpecificFactory {
    static AbstractTaskDAO getCompileTask(String language) {
        switch (language) {
            case "cpp":
                return new BuildDAO("g++ Main.cpp -o Main");
            case "c":
                return new BuildDAO("gcc Main.c -o Main -lm");
            case "java":
                return new BuildDAO("javac Main.java");
            default:
                return null;
        }
    }

    static AbstractTaskDAO getBuildTask(String language) {
        switch (language) {
            case "cpp":
                return new BuildDAO("bash", "-c", "cmake ./ && make");
            default:
                return null;
        }
    }

    static String getExtension(String language) {
        switch (language) {
            case "cpp":
                return "cpp";
            case "c":
                return "c";
            case "python":
                return "py";
            case "java":
                return "java";
        }

        throw new IllegalArgumentException("Unknown language");
    }

    static String getRunFileTestsCommand(String language) {
        switch (language) {
            case "cpp":
                return "./Main";
            case "c":
                return "./Main";
            case "python":
                return "python Main.py";
            case "java":
                return "java Main";
        }

        throw new IllegalArgumentException("Unknown language");
    }
}
