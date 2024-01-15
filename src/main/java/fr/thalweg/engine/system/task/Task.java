package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public abstract class Task extends IteratingSystem {
    public Task(Family family) {
        super(family);
    }
}
