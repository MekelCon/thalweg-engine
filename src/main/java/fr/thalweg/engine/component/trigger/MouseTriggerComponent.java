package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import lombok.Builder;

@Builder
public class MouseTriggerComponent implements Component {

    public Polygon polygon;
}
