package fr.thalweg.engine.component.task;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import fr.thalweg.gen.engine.model.OverTimeTaskData;

public abstract class OverTimeTaskComponent implements TaskComponent {

    public float time;
    public @Null Interpolation interpolation;
    public boolean reverse, began, complete;

    public abstract OverTimeTaskData getData();

    @Override
    public void reset() {
        time = 0;
        interpolation = null;
        reverse = false;
        began = false;
        complete = false;
    }
}
