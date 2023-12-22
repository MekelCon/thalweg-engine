package fr.thalweg.engine.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationUtilsTest {

    @Test
    public void notNull() {
        assertNotNull(ValidationUtils.get());
    }

    @Test
    public void refuseNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtils.get().notNull(null));

    }

    @Test
    public void refuseEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtils.get().notEmpty(""));
    }

    @Test
    public void refuseEmptyForNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtils.get().notEmpty(null));
    }
}
