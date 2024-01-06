package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.math.Vector2;
import fr.thalweg.engine.infra.schema.Scale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToVector2ScaleTest {

    @Test
    void defaultValueForNullInput() {
        Vector2 vector2 = assertDoesNotThrow(
                () -> ToVector2Scale.from(null));
        assertEquals(new Vector2(1, 1), vector2);
    }

    @Test
    void useInput() {
        Scale scale = Scale.builder().x(5f).y(5f).build();
        Vector2 vector2 = assertDoesNotThrow(
                () -> ToVector2Scale.from(scale));
        assertEquals(new Vector2(5f, 5f), vector2);
    }

}