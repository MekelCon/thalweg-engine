package fr.thalweg.engine.model;

import fr.thalweg.engine.validator.ValidationUtils;
import lombok.Getter;

@Getter
public class Directory {

    private final String originalPath;

    public Directory(String value) {
        Directory.assertValid(value);
        // Remove trailing / if exist
        this.originalPath = value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }

    public static void assertValid(String value) {
        ValidationUtils.get()
                .notNull(value)
                .notEmpty(value);
    }

    public String getSubFolder(String folder) {
        return this.originalPath + "/" + folder;
    }
}
