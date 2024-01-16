package fr.thalweg.engine.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SpriteComponentTest {

    @Test
    public void refuseNull() {
        assertThrows(
                NullPointerException.class,
                () -> SpriteComponent
                        .builder()
                        .build());
    }
}