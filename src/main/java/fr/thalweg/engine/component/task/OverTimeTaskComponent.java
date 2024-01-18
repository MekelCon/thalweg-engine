package fr.thalweg.engine.component.task;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;

public class OverTimeTaskComponent implements TaskComponent {

    public float duration, time, delay;
    public @Null Interpolation interpolation;
    public boolean reverse, began, complete;

    @Override
    public void reset() {
        delay = 0;
        duration = 0;
        time = 0;
        reverse = false;
        began = false;
        complete = false;
    }
}
