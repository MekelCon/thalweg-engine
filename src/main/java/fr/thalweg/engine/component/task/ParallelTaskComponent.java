package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class ParallelTaskComponent implements TaskComponent {
    public Array<TaskComponent> components;
    public Array<Entity> executors;

    @Override
    public void reset() {
        components = null;
        executors = null;
    }
}
