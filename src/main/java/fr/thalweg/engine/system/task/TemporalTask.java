package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import fr.thalweg.gen.engine.model.TemporalTaskData;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class TemporalTask implements Task {

    private float duration, time;
    private @Null Interpolation interpolation;
    private boolean reverse, began, complete;

    public TemporalTask(TemporalTaskData data) {
        this.duration = data.getDuration();
        // TODO interpolation
    }

    @Override
    public boolean work(Entity entity, float deltaTime) {
        if (!began) {
            begin(entity);
            began = true;
        }
        time += deltaTime;
        complete = time >= duration;
        float percent = complete ? 1 : time / duration;
        if (interpolation != null) percent = interpolation.apply(percent);
        update(entity, reverse ? 1 - percent : percent);
        if (complete) end(entity);
        return complete;
    }

    protected void begin(Entity entity) {
    }

    protected void end(Entity entity) {
    }

    abstract protected void update(Entity entity, float percent);

    @Override
    public void added() {
        time = 0;
        began = false;
        complete = false;
    }
}
