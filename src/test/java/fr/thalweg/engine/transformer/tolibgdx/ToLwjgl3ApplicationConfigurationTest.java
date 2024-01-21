package fr.thalweg.engine.transformer.tolibgdx;

import fr.thalweg.engine.infra.data.Lwjgl3ApplicationConfigData;
import fr.thalweg.engine.infra.data.WidthAndHeightData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToLwjgl3ApplicationConfigurationTest {
    private static Lwjgl3ApplicationConfigData createDefault() {
        return new Lwjgl3ApplicationConfigData().title("foo").foregroundFPS(20).useVSync(true);
    }

    private static Lwjgl3ApplicationConfigData createDefaultWindowed() {
        return createDefault().windowed(new WidthAndHeightData().width(54321).height(123456));
    }

    @Test
    void nullNotHandled() {
        assertThrows(NullPointerException.class, () -> ToLwjgl3ApplicationConfiguration.from(null));
    }

    @Test
    void allFieldsNotWindowed() {
        Lwjgl3ApplicationConfigData source = createDefault();
        assertDoesNotThrow(() -> ToLwjgl3ApplicationConfiguration.from(source));
    }

    @Test
    void allFieldsWindowed() {
        Lwjgl3ApplicationConfigData source = createDefaultWindowed();
        assertDoesNotThrow(() -> ToLwjgl3ApplicationConfiguration.from(source));
    }
}