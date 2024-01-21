package fr.thalweg.engine.transformer.toECS;

import fr.thalweg.engine.infra.data.PositionData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToZIndexTest {

    @Test
    public void defaultIsZero() {
        assertEquals(0, ToZIndex.from(null));
    }

    @Test
    public void useZOfPosition() {
        PositionData pos = new PositionData()
                .x(1f)
                .y(2f)
                .z(3);
        assertEquals(pos.getZ(), ToZIndex.from(pos));
    }

}