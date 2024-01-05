package fr.thalweg.engine.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ActorEntityTest {

    @Test
    public void create() {
        assertDoesNotThrow(ActorEntity::new);
    }

}