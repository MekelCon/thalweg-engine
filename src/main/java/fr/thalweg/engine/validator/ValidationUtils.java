package fr.thalweg.engine.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {

    private static final ValidationUtils INSTANCE = new ValidationUtils();

    public static void throwDedicatedException(String message) {
        throw new IllegalArgumentException(message);
    }

    public static ValidationUtils get() {
        return ValidationUtils.INSTANCE;
    }

    public ValidationUtils notNull(Object value) {
        if (value == null) {
            throwDedicatedException("Value can't be null");
        }
        return INSTANCE;
    }

    public ValidationUtils notEmpty(String value) {
        if (value == null || value.isEmpty()) {
            throwDedicatedException("Value can't be null");
        }
        return INSTANCE;
    }

}
