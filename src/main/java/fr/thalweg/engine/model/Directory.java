package fr.thalweg.engine.model;

import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.validator.ValidationUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Directory {

    private final String originalPath;

    public static Directory of(String value) {
        Directory.assertValid(value);
        return new Directory(value);
    }

    public static void assertValid(String value) {
        ValidationUtils.get()
                .notNull(value)
                .notEmpty(value);
        if (!Gdx.files.classpath(value).exists()) {
            ValidationUtils.throwDedicatedException("Directory should exist");
        }
    }

    public String get() {
        return originalPath;
    }
}
