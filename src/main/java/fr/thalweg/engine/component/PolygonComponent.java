package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import lombok.Builder;

@Builder
public class PolygonComponent implements Component {

    public Polygon polygon;
}
