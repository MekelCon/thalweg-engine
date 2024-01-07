package fr.thalweg.engine.component.trigger;

import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.system.task.Task;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MouseTriggerComponent implements TriggerComponent {
    @Builder.Default
    public Array<Task> onMouseEnter = new Array<>();
    @Builder.Default
    public Array<Task> onMouseLeave = new Array<>();
}
