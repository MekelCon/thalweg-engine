package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ZIndexComponent implements Component, Pool.Poolable {
    public int zIndex;

    @Override
    public void reset() {
        zIndex = 0;
    }
}
