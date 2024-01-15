package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import lombok.Builder;

@Builder
public class MouseTriggerComponent implements Component {
    public Component onMouseEnter;
    public Component onMouseLeave;
}
