package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class ParallelTaskComponent implements Component {
    public Array<Component> components;
    public Array<Entity> executors;
}
