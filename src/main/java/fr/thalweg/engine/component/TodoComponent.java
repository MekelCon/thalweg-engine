package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.Todo;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class TodoComponent implements Component {
    @NonNull
    public Array<Todo> todos;
}
