package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.task.OverTimeTaskComponent;
import fr.thalweg.engine.system.task.Task;
import fr.thalweg.gen.engine.model.OverTimeTaskData;

public abstract class OverTimeTask extends Task {

    private final ComponentMapper<? extends OverTimeTaskComponent<? extends OverTimeTaskData>> cm;

    public OverTimeTask(Class<? extends OverTimeTaskComponent<? extends OverTimeTaskData>> clazz) {
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
        if (overTimeTaskComponent.data.delay > 0) {
            overTimeTaskComponent.data.delay = Math.max(0, overTimeTaskComponent.data.delay - deltaTime);
            overTimeTaskComponent.time = Math.max(0, overTimeTaskComponent.time - overTimeTaskComponent.data.delay);
        }
        overTimeTaskComponent.complete = overTimeTaskComponent.time >= overTimeTaskComponent.data.duration;
        float percent = overTimeTaskComponent.complete ? 1 : overTimeTaskComponent.time / overTimeTaskComponent.data.duration;
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
