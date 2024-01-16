package fr.thalweg.engine.entity;

import org.junit.jupiter.api.Test;

class ActorEntityTest {

    @Test
    public void create() {
        assertDoesNotThrow(ActorEntity::new);
    }

}