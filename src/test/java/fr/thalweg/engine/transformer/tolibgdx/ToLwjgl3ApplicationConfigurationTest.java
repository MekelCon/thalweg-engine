package fr.thalweg.engine.transformer.tolibgdx;

import fr.thalweg.engine.infra.schema.WindowedSchema;
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
        WindowedSchema windowedSchema = new WindowedSchema();
        windowedSchema.setHeight(123456);
        windowedSchema.setWidth(54321);
        result.setWindowedSchema(windowedSchema);
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