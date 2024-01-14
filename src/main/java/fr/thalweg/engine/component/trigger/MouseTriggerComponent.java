package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import fr.thalweg.engine.component.task.TaskComponent;
import lombok.Builder;

@Builder
public class MouseTriggerComponent implements Component {

    public TaskComponent onMouseEnter;

    public TaskComponent onMouseLeave;
}
