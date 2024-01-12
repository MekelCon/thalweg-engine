package fr.thalweg.engine.model;

import fr.thalweg.engine.validator.ValidationUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Directory {

    private final String originalPath;

    public static Directory of(String value) {
        Directory.assertValid(value);
        // Remove trailing / if exist
        value = value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
        return new Directory(value);
    }

    public static void assertValid(String value) {
        ValidationUtils.get()
                .notNull(value)
                .notEmpty(value);
    }

    public String get() {
        return originalPath;
    }

    public String getSubFolder(String folder) {
        return this.originalPath + "/" + folder;
    }
}
