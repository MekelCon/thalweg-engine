package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.task.OverTimeTaskComponent;
import fr.thalweg.engine.system.task.Task;

public abstract class OverTimeTask extends Task {
    private final ComponentMapper<OverTimeTaskComponent> cm;

    public OverTimeTask(Family family) {
        super(family);
        this.cm = ComponentMapper.getFor(OverTimeTaskComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var overTimeTaskComponent = cm.get(entity);
        if (overTimeTaskComponent == null) {
            overTimeTaskComponent = getEngine().createComponent(OverTimeTaskComponent.class);
            overTimeTaskComponent.duration = getDuration(entity);
            overTimeTaskComponent.delay = getDelay(entity);
            entity.add(overTimeTaskComponent);
        }
        overTimeTaskComponent.time += deltaTime;
        if (!overTimeTaskComponent.began) {
            begin(entity);
            overTimeTaskComponent.began = true;
        }
        if (overTimeTaskComponent.delay > 0) {
            overTimeTaskComponent.delay = Math.max(
                    0,
                    overTimeTaskComponent.delay - deltaTime);
            overTimeTaskComponent.time = Math.max(
                    0,
                    overTimeTaskComponent.time - overTimeTaskComponent.delay);
        }
        overTimeTaskComponent.complete = overTimeTaskComponent.time >= overTimeTaskComponent.duration;
        float percent = overTimeTaskComponent.complete ? 1 : overTimeTaskComponent.time / overTimeTaskComponent.duration;
        if (overTimeTaskComponent.interpolation != null)
            percent = overTimeTaskComponent.interpolation.apply(percent);
        update(entity, overTimeTaskComponent.reverse ? 1 - percent : percent);
        if (overTimeTaskComponent.complete) {
            end(entity);
            entity.removeAll();
            getEngine().removeEntity(entity);
        }

    }

    protected abstract float getDuration(Entity entity);

    protected abstract float getDelay(Entity entity);

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
