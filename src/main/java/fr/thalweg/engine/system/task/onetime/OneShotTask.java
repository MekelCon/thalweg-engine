package fr.thalweg.engine.system.task.onetime;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.system.task.Task;

public abstract class OneShotTask extends Task {

    public OneShotTask(Family family) {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        work(entity);
        entity.removeAll();
        getEngine().removeEntity(entity);
    }

    abstract protected void work(Entity entity);
}
