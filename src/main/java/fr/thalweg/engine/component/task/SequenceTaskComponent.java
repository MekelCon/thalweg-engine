package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class SequenceTaskComponent implements TaskComponent {
    public Array<TaskComponent> components;
    public int currentIndex;
    public Entity executor;

    @Override
    public void reset() {
        components = null;
        currentIndex = 0;
        executor = null;
    }
}
