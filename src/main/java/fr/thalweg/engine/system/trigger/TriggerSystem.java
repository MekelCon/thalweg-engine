package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.TodoComponent;
import fr.thalweg.engine.component.trigger.todo.Todo;

public interface TriggerSystem extends Component {

    default void addTodo(Entity entity, Array<Todo> todos) {
        entity.add(TodoComponent.builder()
                .todos(todos)
                .build());
    }
}