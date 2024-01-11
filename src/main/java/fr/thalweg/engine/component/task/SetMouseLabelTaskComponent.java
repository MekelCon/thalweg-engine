package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import fr.thalweg.gen.engine.model.SetMouseLabelTaskData;
import lombok.Builder;

@Builder
public class SetMouseLabelTaskComponent implements Component {

    public SetMouseLabelTaskData data;
}
