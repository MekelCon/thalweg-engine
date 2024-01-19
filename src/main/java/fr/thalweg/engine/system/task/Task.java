package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.TaskComponent;

public abstract class Task extends IteratingSystem {
    public Task(Class<? extends TaskComponent> clazz) {
        // Only working task should be iterated
        super(Family.all(clazz, WorkingFlag.class).get());
    }
}
