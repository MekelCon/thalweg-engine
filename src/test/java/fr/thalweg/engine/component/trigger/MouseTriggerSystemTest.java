package fr.thalweg.engine.component.trigger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MouseTriggerSystemTest {

    @Test
    void builder() {
        assertDoesNotThrow(
                () -> MouseTriggerComponent.builder().build());
    }
}