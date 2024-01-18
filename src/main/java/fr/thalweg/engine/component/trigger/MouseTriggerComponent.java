package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.gen.engine.model.TaskData;

public class MouseTriggerComponent implements Component, Pool.Poolable {
    public TaskData onMouseEnter;
    public TaskData onMouseLeave;

    @Override
    public void reset() {
        onMouseEnter = null;
        onMouseLeave = null;
    }
}
