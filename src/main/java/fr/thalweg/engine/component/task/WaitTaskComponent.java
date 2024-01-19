package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.gen.engine.model.OverTimeTaskData;

public class WaitTaskComponent implements Component, Pool.Poolable {

    public OverTimeTaskData data;

    public Interpolation interpolation;

    @Override
    public void reset() {
        // Nothing to reset
    }
}
