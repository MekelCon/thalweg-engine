package fr.thalweg.engine.component.trigger;

import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.Todo;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MouseTriggerComponent extends TriggerComponent {

    @Builder.Default
    public Array<Todo> onMouseEnter = new Array<>();
    @Builder.Default
    public Array<Todo> onMouseLeave = new Array<>();

    public void mouseLeave() {
        addTodos(onMouseLeave);
    }

    public void mouseEnter() {
        addTodos(onMouseEnter);
    }
}
