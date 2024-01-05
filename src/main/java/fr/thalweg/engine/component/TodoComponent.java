package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.trigger.todo.Todo;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class TodoComponent implements Component {
    @NonNull
    public Array<Todo> todos;
}
