package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class SequenceTaskComponent implements Component, Pool.Poolable {

    public Array<Component> components = new Array<>();
    public int currentIndex;
    public Entity executor;

    @Override
    public void reset() {
        components.clear();
        currentIndex = 0;
        executor = null;
    }
}
