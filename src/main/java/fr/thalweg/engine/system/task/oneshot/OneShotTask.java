package fr.thalweg.engine.system.task.oneshot;

import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.task.TaskComp;
import fr.thalweg.engine.system.task.Task;

public abstract class OneShotTask extends Task {

    public OneShotTask(Class<? extends TaskComp> clazz) {
        super(clazz);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        work(entity);
        entity.removeAll();
        getEngine().removeEntity(entity);
    }

    abstract protected void work(Entity entity);
}
