package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import lombok.Builder;

@Builder
public class TemporalTaskComponent implements Component {

    public float time, percent;
    public @Null Interpolation interpolation;
    public boolean reverse, began, complete;
}
