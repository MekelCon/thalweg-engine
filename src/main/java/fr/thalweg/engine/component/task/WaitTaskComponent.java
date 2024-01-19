package fr.thalweg.engine.component.task;

import com.badlogic.gdx.math.Interpolation;
import fr.thalweg.gen.engine.model.OverTimeTaskData;

public class WaitTaskComponent extends OverTimeTaskComponent {

    public OverTimeTaskData data;
    public Interpolation interpolation;

    @Override
    public OverTimeTaskData getData() {
        return data;
    }
}
