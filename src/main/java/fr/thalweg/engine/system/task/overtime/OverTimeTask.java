package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Interpolation;
import fr.thalweg.engine.component.task.OverTimeTaskComponent;
import fr.thalweg.engine.system.task.Task;
import fr.thalweg.gen.engine.model.OverTimeTaskData;

public abstract class OverTimeTask extends Task {
    private final ComponentMapper<OverTimeTaskComponent> cm;

    public OverTimeTask(Family family) {
        super(family);
        this.cm = ComponentMapper.getFor(OverTimeTaskComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        OverTimeTaskComponent overTimeTaskComponent = cm.get(entity);
        if (overTimeTaskComponent == null) {
            overTimeTaskComponent = getEngine().createComponent(OverTimeTaskComponent.class);
            overTimeTaskComponent.data = new OverTimeTaskData()
                    .delay(getDelay(entity))
                    .duration(getDuration(entity));
            overTimeTaskComponent.interpolation = getInterpolation(entity);
            entity.add(overTimeTaskComponent);
        }


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

    protected abstract float getDelay(Entity entity);

    protected abstract float getDuration(Entity entity);

    protected abstract Interpolation getInterpolation(Entity entity);

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
