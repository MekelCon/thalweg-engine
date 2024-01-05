package fr.thalweg.engine.component.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.Todo;
import lombok.Builder;

@Builder
public class MouseTriggerComponent implements Component {
    @Builder.Default
    public Array<Todo> onMouseEnter = new Array<>();
    @Builder.Default
    public Array<Todo> onMouseLeave = new Array<>();
    @Builder.Default
    public Array<Todo> toAct = new Array<>();

    public void mouseLeave() {
        toAct.addAll(onMouseLeave);
    }

    public void mouseEnter() {
        toAct.addAll(onMouseEnter);
    }
}
