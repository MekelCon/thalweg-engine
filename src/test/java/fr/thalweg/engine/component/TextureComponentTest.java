package fr.thalweg.engine.component;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import fr.thalweg.engine.utils.JsonYamlThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TextureComponentTest {

    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new JsonYamlThalwegGame());
    }

    @Test
    public void refuseNull() {
        assertThrows(
                NullPointerException.class,
                () -> TextureComponent
                        .builder()
                        .region(null)
                        .build());
    }

}