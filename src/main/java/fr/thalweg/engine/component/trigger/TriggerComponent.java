package fr.thalweg.engine.component.trigger;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.Todo;
import fr.thalweg.engine.component.TodoComponent;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TriggerComponent implements Component {
    @NonNull
    private Entity holder;

    protected void addTodos(Array<Todo> todos) {
        holder.add(TodoComponent.builder()
                // Use copy to avoid side effect on removal
                .todos(new Array<>(todos))
                .build());
    }
}
