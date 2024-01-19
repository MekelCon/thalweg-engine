package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Pool;

public class PolygonComponent implements Component, Pool.Poolable {

    public Polygon polygon;

    @Override
    public void reset() {
        polygon = null;
    }
}
