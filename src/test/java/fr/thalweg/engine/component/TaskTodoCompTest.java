package fr.thalweg.engine.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTodoCompTest {

    @Test
    void refuseNull() {
        assertThrows(
                NullPointerException.class,
                () -> TaskTodoComp.builder().build());
    }
}