package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import fr.thalweg.gen.engine.model.LogTaskData;
import lombok.Builder;

@Builder
public class LogTaskComponent implements Component {

    public LogTaskData data;
}
