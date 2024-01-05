package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class PolygonComponent implements Component {

    @NonNull
    public Polygon polygon;
}
