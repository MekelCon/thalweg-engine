package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.engine.component.task.TaskComp;

public class MouseTriggerComponent implements Component, Pool.Poolable {
    public TaskComp onMouseEnter;
    public TaskComp onMouseLeave;

    @Override
    public void reset() {
        onMouseEnter = null;
        onMouseLeave = null;
    }
}
