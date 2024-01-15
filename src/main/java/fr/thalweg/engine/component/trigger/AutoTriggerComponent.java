package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import fr.thalweg.engine.component.TaskComponent;
import lombok.Builder;

@Builder
public class AutoTriggerComponent implements Component {
    public TaskComponent todo;
}
