package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.engine.component.task.TaskComp;

public class AutoTriggerComponent implements Component, Pool.Poolable {
    public TaskComp todo;

    @Override
    public void reset() {
        todo = null;
    }
}
