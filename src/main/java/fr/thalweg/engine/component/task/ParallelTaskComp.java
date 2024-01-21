package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class ParallelTaskComp extends ArrayTaskComp {
    public Array<Entity> _executors;

    public boolean _started;

    @Override
    public void build() {
        super.build();
        _executors = new Array<>(todos.size);
    }

    @Override
    public void reset() {
        super.reset();
        _executors.clear();
        _started = false;
    }
}
