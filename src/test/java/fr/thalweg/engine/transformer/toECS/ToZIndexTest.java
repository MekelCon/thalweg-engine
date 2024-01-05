package fr.thalweg.engine.transformer.toECS;

import fr.thalweg.engine.gen.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToZIndexTest {

    @Test
    public void defaultIsZero() {
        assertEquals(0, ToZIndex.from(null));
    }

    @Test
    public void useZOfPosition() {
        Position pos = new Position()
                .withX(1)
                .withY(2)
                .withZ(3);
        assertEquals(pos.getZ(), ToZIndex.from(pos));
    }

}