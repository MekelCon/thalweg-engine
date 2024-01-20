package fr.thalweg.engine.transformer.tolibgdx;

import fr.thalweg.engine.infra.data.LogLevelEnumData;
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
        for (LogLevelEnumData logLevel : LogLevelEnumData.values())
            assertDoesNotThrow(() -> ToLogLevel.from(logLevel));
    }
}