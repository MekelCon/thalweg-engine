package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.task.TemporalTaskComponent;

public abstract class TemporalTask extends Task {
    private final ComponentMapper<TemporalTaskComponent> cm;

    public TemporalTask(Family family) {
        super(family);
        this.cm = ComponentMapper.getFor(TemporalTaskComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var temporalTaskComponent = cm.get(entity);
        if (temporalTaskComponent == null) {
            temporalTaskComponent = TemporalTaskComponent.builder()
                    .build();
            entity.add(temporalTaskComponent);
        }
        if (!temporalTaskComponent.began) {
            begin(entity);
            temporalTaskComponent.began = true;
        }
        temporalTaskComponent.time += deltaTime;
        temporalTaskComponent.complete = temporalTaskComponent.time >= getDuration(entity);
        float percent = temporalTaskComponent.complete ? 1 : temporalTaskComponent.time / getDuration(entity);
        if (temporalTaskComponent.interpolation != null) percent = temporalTaskComponent.interpolation.apply(percent);
        update(entity, temporalTaskComponent.reverse ? 1 - percent : percent);
        if (temporalTaskComponent.complete) {
            end(entity);
            entity.removeAll();
            getEngine().removeEntity(entity);
        }
    }

    protected abstract float getDuration(Entity entity);

    protected void begin(Entity entity) {
    }

    protected void end(Entity entity) {
    }

    abstract protected void update(Entity entity, float percent);
}
