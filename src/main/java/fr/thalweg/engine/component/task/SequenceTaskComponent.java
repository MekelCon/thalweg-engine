package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class SequenceTaskComponent implements TaskComponent {

    public Array<Component> components = new Array<>();
    public int _currentIndex;
    public Entity _executor;

    @Override
    public void reset() {
        components.clear();
        _currentIndex = 0;
        _executor = null;
    }
}
