package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.task.OverTimeTaskComponent;
import fr.thalweg.engine.system.task.Task;

public abstract class OverTimeTask extends Task {

    private final ComponentMapper<? extends OverTimeTaskComponent> cm;

    public OverTimeTask(Class<? extends OverTimeTaskComponent> clazz) {
        super(clazz);
        this.cm = ComponentMapper.getFor(clazz);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var overTimeTaskComponent = cm.get(entity);
        overTimeTaskComponent.time += deltaTime;
        if (!overTimeTaskComponent.began) {
            begin(entity);
            overTimeTaskComponent.began = true;
        }
        // Decrease the delay until 0
        if (overTimeTaskComponent.getData().delay > 0) {
            overTimeTaskComponent.getData().delay = Math.max(0, overTimeTaskComponent.getData().delay - deltaTime);
            overTimeTaskComponent.time = Math.max(0, overTimeTaskComponent.time - overTimeTaskComponent.getData().delay);
        }
        overTimeTaskComponent.complete = overTimeTaskComponent.time >= overTimeTaskComponent.getData().duration;
        float percent = overTimeTaskComponent.complete ? 1 : overTimeTaskComponent.time / overTimeTaskComponent.getData().duration;
        if (overTimeTaskComponent.interpolation != null)
            percent = overTimeTaskComponent.interpolation.apply(percent);
        update(entity, overTimeTaskComponent.reverse ? 1 - percent : percent);
        if (overTimeTaskComponent.complete) {
            end(entity);
            entity.removeAll();
            getEngine().removeEntity(entity);
        }
    }

    /**
     * Will be cal even before the delay, allow the initialization
     *
     * @param entity The current processed entity
     */
    protected void begin(Entity entity) {
    }

    protected void end(Entity entity) {
    }

    abstract protected void update(Entity entity, float percent);
}
