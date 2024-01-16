package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.math.Vector2;
import fr.thalweg.gen.engine.model.ScaleData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToVector2ScaleTestSchema {

    @Test
    void defaultValueForNullInput() {
        Vector2 vector2 = assertDoesNotThrow(
                () -> ToVector2Scale.from(null));
        assertEquals(new Vector2(1, 1), vector2);
    }

    @Test
    void useInput() {
        ScaleData scaleData = new ScaleData().x(5f).y(5f);
        Vector2 vector2 = assertDoesNotThrow(
                () -> ToVector2Scale.from(scaleData));
        assertEquals(new Vector2(5f, 5f), vector2);
    }

}