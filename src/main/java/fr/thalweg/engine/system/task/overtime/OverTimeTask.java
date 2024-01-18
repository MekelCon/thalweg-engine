package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.task.OverTimeTaskComponent;
import fr.thalweg.engine.system.task.Task;

public abstract class OverTimeTask<T extends OverTimeTaskComponent> extends Task {
    private final ComponentMapper<T> cm;

    public Class<T> aClass;

    public OverTimeTask(Family family, Class<T> subClass) {
        super(family);
        this.aClass = subClass;
        this.cm = ComponentMapper.getFor(aClass);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        T overTimeTaskComponent = cm.get(entity);
        overTimeTaskComponent.time += deltaTime;
        if (!overTimeTaskComponent.began) {
            begin(entity);
            overTimeTaskComponent.began = true;
        }
        // Decrease the delay until 0
        if (overTimeTaskComponent.data.getDelay() > 0) {
            overTimeTaskComponent.data.setDelay(Math.max(
                    0,
                    overTimeTaskComponent.data.getDelay() - deltaTime));
            overTimeTaskComponent.time = Math.max(
                    0,
                    overTimeTaskComponent.time - overTimeTaskComponent.data.getDelay());
        }
        overTimeTaskComponent.complete = overTimeTaskComponent.time >= overTimeTaskComponent.data.getDuration();
        float percent = overTimeTaskComponent.complete ? 1 : overTimeTaskComponent.time / overTimeTaskComponent.data.getDuration();
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
     * @param entity
     */
    protected void begin(Entity entity) {
    }

    protected void end(Entity entity) {
    }

    abstract protected void update(Entity entity, float percent);
}
