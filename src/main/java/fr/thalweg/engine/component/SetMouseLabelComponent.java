package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import fr.thalweg.engine.system.task.SetMouseLabelTask;
import lombok.Builder;

@Builder
public class SetMouseLabelComponent implements Component {
    public SetMouseLabelTask caller;
}
