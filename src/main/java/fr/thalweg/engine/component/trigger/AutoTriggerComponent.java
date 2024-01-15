package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import lombok.Builder;

@Builder
public class AutoTriggerComponent implements Component {
    public Component todo;
}
