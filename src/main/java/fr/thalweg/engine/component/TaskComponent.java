package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import fr.thalweg.engine.system.task.Task;
import lombok.Builder;

@Builder
public class TaskComponent implements Component {
    public Task task;
}
