package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Entity;

public class SequenceTaskComp extends ArrayTaskComp {

    public int _currentIndex;
    public Entity _executor;

    @Override
    public void reset() {
        super.reset();
        _currentIndex = 0;
        _executor = null;
    }
}
