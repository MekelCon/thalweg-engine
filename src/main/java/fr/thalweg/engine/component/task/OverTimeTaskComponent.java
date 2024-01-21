package fr.thalweg.engine.component.task;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import fr.thalweg.engine.infra.data.task.OverTimeTaskData;

public abstract class OverTimeTaskComponent<T extends OverTimeTaskData> implements TaskComponent {

    public T data;
    public float _time;
    public @Null Interpolation interpolation;
    public boolean _reverse, _began, _started, _complete;

    @Override
    public void reset() {
        data = null;
        _time = 0;
        interpolation = null;
        _reverse = false;
        _began = false;
        _started = false;
        _complete = false;
    }
}
