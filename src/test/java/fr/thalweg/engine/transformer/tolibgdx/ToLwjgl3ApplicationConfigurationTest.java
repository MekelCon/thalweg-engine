package fr.thalweg.engine.transformer.tolibgdx;

import fr.thalweg.engine.gen.Lwjgl3ApplicationConfigurationSchema;
import fr.thalweg.engine.gen.Windowed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToLwjgl3ApplicationConfigurationTest {
    private static Lwjgl3ApplicationConfigurationSchema createDefault() {
        Lwjgl3ApplicationConfigurationSchema result = new Lwjgl3ApplicationConfigurationSchema();
        result.setTitle("foo");
        result.setForegroundFPS(20);
        result.setUseVSync(true);
        return result;
    }

    private static Lwjgl3ApplicationConfigurationSchema createDefaultWindowed() {
        Lwjgl3ApplicationConfigurationSchema result = createDefault();
        Windowed windowed = new Windowed();
        windowed.setHeight(123456);
        windowed.setWidth(54321);
        result.setWindowed(windowed);
        return result;
    }

    @Test
    void nullNotHandled() {
        assertThrows(
                NullPointerException.class,
                () -> ToLwjgl3ApplicationConfiguration.from(null));
    }

    @Test
    void allFieldsNotWindowed() {
        Lwjgl3ApplicationConfigurationSchema source = createDefault();
        assertDoesNotThrow(() -> ToLwjgl3ApplicationConfiguration.from(source));
    }

    @Test
    void allFieldsWindowed() {
        Lwjgl3ApplicationConfigurationSchema source = createDefaultWindowed();
        assertDoesNotThrow(() -> ToLwjgl3ApplicationConfiguration.from(source));
    }
}