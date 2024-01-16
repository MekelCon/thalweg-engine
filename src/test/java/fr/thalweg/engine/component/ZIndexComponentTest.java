package fr.thalweg.engine.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZIndexComponentTest {

    @Test
    void defaultValue() {
        ZIndexComponent zIndexComponent = assertDoesNotThrow(
                () -> ZIndexComponent.builder().build());
        assertEquals(0, zIndexComponent.zIndex);
    }
}