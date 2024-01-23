package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.task.OverTimeTaskComp;
import fr.thalweg.engine.system.task.Task;

public abstract class OverTimeTask extends Task {

    private final ComponentMapper<? extends OverTimeTaskComp> cm;

    public OverTimeTask(Class<? extends OverTimeTaskComp> clazz) {
        super(clazz);
        this.cm = ComponentMapper.getFor(clazz);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var overTimeTaskComponent = cm.get(entity);
        overTimeTaskComponent._time += deltaTime;
        if (!overTimeTaskComponent._began) {
            begin(entity);
            overTimeTaskComponent._began = true;
        }
        // Decrease the delay until 0
        if (overTimeTaskComponent.delay > 0) {
            overTimeTaskComponent.delay = Math.max(0, overTimeTaskComponent.delay - deltaTime);
            overTimeTaskComponent._time = Math.max(0, overTimeTaskComponent._time - overTimeTaskComponent.delay);
        } else {
            if (!overTimeTaskComponent._started) {
                start(entity);
                // we want to come here only once
                overTimeTaskComponent._started = true;
            }
            overTimeTaskComponent._complete = overTimeTaskComponent._time >= overTimeTaskComponent.duration;
            float percent = overTimeTaskComponent._complete ? 1 : overTimeTaskComponent._time / overTimeTaskComponent.duration;
            if (overTimeTaskComponent._interpolation != null)
                percent = overTimeTaskComponent._interpolation.apply(percent);
            update(entity, overTimeTaskComponent.reverse ? 1 - percent : percent);
            if (overTimeTaskComponent._complete) {
                end(entity);
                entity.removeAll();
                getEngine().removeEntity(entity);
            }
        }
    }

    /**
     * Will be call 1time even before the delay, allow the initialization
     *
     * @param entity The current processed entity
     */
    protected void begin(Entity entity) {
    }

    /**
     * Will be call 1 time after the delay passed.
     *
     * @param entity The component holder
     */
    protected void start(Entity entity) {
    }

    /**
     * Call each frame
     *
     * @param entity  The component holder
     * @param percent Calculated based on the duration
     */
    protected void update(Entity entity, float percent) {

    }

    /**
     * Call 1 time at the end
     *
     * @param entity The component holder
     */
    protected void end(Entity entity) {
    }
}
