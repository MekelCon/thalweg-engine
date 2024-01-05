package fr.thalweg.engine.component;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import fr.thalweg.engine.utils.JsonYamlThalwegGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransformComponentTest {


    @BeforeAll
    public static void beforeAll() {
        new HeadlessApplication(new JsonYamlThalwegGame());
    }

    @Test
    public void okDefault() {
        TransformComponent transformComponent = assertDoesNotThrow(
                () -> TransformComponent
                        .builder()
                        .build());

        assertEquals(new Vector3(0, 0, 0), transformComponent.pos);
        assertEquals(new Vector2(1, 1), transformComponent.scale);
        assertEquals(0, transformComponent.rotation);
    }

    @Test
    public void refuseNullPos() {
        assertThrows(
                NullPointerException.class,
                () -> TransformComponent
                        .builder()
                        .pos(null)
                        .build());
    }

    @Test
    public void refuseNullScale() {
        assertThrows(
                NullPointerException.class,
                () -> TransformComponent
                        .builder()
                        .scale(null)
                        .build());
    }

}