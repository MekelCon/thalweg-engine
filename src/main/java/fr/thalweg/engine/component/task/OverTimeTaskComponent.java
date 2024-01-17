package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;

public class OverTimeTaskComponent implements Component {

    public float duration, time;
    public @Null Interpolation interpolation;
    public boolean reverse, began, complete;
}
