package fr.thalweg.engine.tolibgdx;

import fr.thalweg.engine.gen.Lwjgl3ApplicationConfigurationSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToLwjgl3ApplicationConfigurationTest {

    private static Lwjgl3ApplicationConfigurationSchema createDefault() {
        Lwjgl3ApplicationConfigurationSchema result = new Lwjgl3ApplicationConfigurationSchema();
        result.setTitle("foo");
        result.setForegroundFPS(20);
        result.setUseVSync(true);
        result.setWindowedWidth(54321);
        result.setWindowedHeight(123456);
        return result;
    }

    @Test
    void nullNotHandled() {
        assertThrows(
                NullPointerException.class,
                () -> ToLwjgl3ApplicationConfiguration.from(null));
    }

    @Test
    void allFields() {
        Lwjgl3ApplicationConfigurationSchema source = createDefault();
        assertDoesNotThrow(() -> ToLwjgl3ApplicationConfiguration.from(source));
    }
}