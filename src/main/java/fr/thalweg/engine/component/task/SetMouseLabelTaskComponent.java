package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.gen.engine.model.SetMouseLabelTaskData;

public class SetMouseLabelTaskComponent implements Component, Pool.Poolable {

    public SetMouseLabelTaskData data;

    @Override
    public void reset() {
        data = null;
    }
}
