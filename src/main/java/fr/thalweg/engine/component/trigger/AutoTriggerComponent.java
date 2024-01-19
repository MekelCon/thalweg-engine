package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.gen.engine.model.TaskData;

public class AutoTriggerComponent implements Component, Pool.Poolable {
    public TaskData todo;

    @Override
    public void reset() {
        todo = null;
    }
}
