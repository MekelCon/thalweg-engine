package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.infra.schema.task.AbstractTask;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MouseTriggerComponent implements Component {
    @Builder.Default
    public Array<AbstractTask> onMouseEnter = new Array<>();
    @Builder.Default
    public Array<AbstractTask> onMouseLeave = new Array<>();
}
