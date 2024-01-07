package fr.thalweg.engine.transformer.toECS;

import fr.thalweg.engine.infra.schema.PositionSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToZIndexTest {

    @Test
    public void defaultIsZero() {
        assertEquals(0, ToZIndex.from(null));
    }

    @Test
    public void useZOfPosition() {
        PositionSchema pos = PositionSchema.builder()
                .x(1)
                .y(2)
                .z(3)
                .build();
        assertEquals(pos.getZ(), ToZIndex.from(pos));
    }

}