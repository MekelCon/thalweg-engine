package fr.thalweg.engine.component.task;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import fr.thalweg.gen.engine.model.OverTimeTaskData;

public abstract class OverTimeTaskComponent<T extends OverTimeTaskData> implements TaskComponent {

    public T data;
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
