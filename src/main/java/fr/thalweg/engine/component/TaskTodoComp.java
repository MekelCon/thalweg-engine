package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.system.task.Task;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class TaskTodoComp implements Component {
    @NonNull
    public Array<Task> todos;
}
