package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ParallelTaskComponent implements Component, Pool.Poolable {
    public Array<Component> components = new Array<>();
    public Array<Entity> executors = new Array<>();

    public boolean _started;

    @Override
    public void reset() {
        components.clear();
        executors.clear();
        _started = false;
    }
}
