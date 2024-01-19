package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.gen.engine.model.LogTaskData;

public class LogTaskComponent implements Component, Pool.Poolable {

    public LogTaskData data;

    @Override
    public void reset() {
        data = null;
    }
}
