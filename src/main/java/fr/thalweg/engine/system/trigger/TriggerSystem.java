package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.TaskTodoComp;
import fr.thalweg.engine.infra.schema.task.AbstractTask;

public interface TriggerSystem extends Component {

    default void addTodo(Entity entity, Array<AbstractTask> todos) {
        entity.add(TaskTodoComp.builder()
                .todos(todos)
                .build());
    }
}