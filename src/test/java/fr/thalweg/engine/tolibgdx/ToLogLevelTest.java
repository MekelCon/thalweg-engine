package fr.thalweg.engine.tolibgdx;

import fr.thalweg.engine.gen.GdxConfigurationSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToLogLevelTest {

    @Test
    void nullNotHandled() {
        assertThrows(
                NullPointerException.class,
                () -> ToLogLevel.from(null));
    }

    @Test
    void allValuesConverted() {
        for (GdxConfigurationSchema.LogLevel logLevel : GdxConfigurationSchema.LogLevel.values())
            assertDoesNotThrow(() -> ToLogLevel.from(logLevel));
    }

}