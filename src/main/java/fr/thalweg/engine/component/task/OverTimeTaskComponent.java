package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import fr.thalweg.gen.engine.model.OverTimeTaskData;

public class OverTimeTaskComponent implements Component, Pool.Poolable {

    public OverTimeTaskData data;
    public float time;

    public @Null Interpolation interpolation;
    public boolean reverse, began, complete;

    @Override
    public void reset() {
        data = null;
        time = 0;
        interpolation = null;
        reverse = false;
        began = false;
        complete = false;
    }
}
