package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Entity;

public interface Task {

    boolean work(Entity entity, float deltaTime);

    default void added() {
        // Nothing to do, just here to avoid forced override
    }
}
