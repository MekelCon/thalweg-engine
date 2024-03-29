package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.math.Vector2;
import fr.thalweg.engine.infra.data.PositionData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToVector2PositionTestSchema {

    @Test
    public void defaultIsZeroZero() {
        Vector2 pos = ToVector2Position.from(null);
        assertEquals(0, pos.x);
        assertEquals(0, pos.y);
    }

    @Test
    public void useXYOfPosition() {
        PositionData pos = new PositionData()
                .x(1f)
                .y(2f)
                .z(3);
        Vector2 vector2Pos = ToVector2Position.from(pos);
        assertEquals(pos.getX(), vector2Pos.x);
        assertEquals(pos.getY(), vector2Pos.y);
    }
}