package fr.thalweg.engine.component.task;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import fr.thalweg.engine.infra.data.InterpolationData;
import fr.thalweg.engine.transformer.tolibgdx.ToInterpolation;

public abstract class OverTimeTaskComp extends TaskComp {

    public float duration = 0f;
    public InterpolationData interpolation;
    public float delay = 0f;
    public float _time;
    public @Null Interpolation _interpolation;
    public boolean reverse, _began, _started, _complete;

    @Override
    public void build() {
        super.build();
        _interpolation = ToInterpolation.from(interpolation);
    }

    @Override
    public void reset() {
        super.reset();
        duration = 0;
        interpolation = null;
        delay = 0;
        _time = 0;
        _interpolation = null;
        reverse = false;
        _began = false;
        _started = false;
        _complete = false;
    }
}
