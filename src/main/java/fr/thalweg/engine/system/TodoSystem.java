package fr.thalweg.engine.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import fr.thalweg.engine.component.trigger.todo.Todo;
import fr.thalweg.engine.component.TodoComponent;

public class TodoSystem extends IteratingSystem {

    private final ComponentMapper<TodoComponent> todoComponentMapper;

    public TodoSystem() {
        super(Family.all(TodoComponent.class).get());
        this.todoComponentMapper = ComponentMapper.getFor(TodoComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TodoComponent todoComponent = todoComponentMapper.get(entity);
        for (int i = 0; i < todoComponent.todos.size; i++) {
            Todo todo = todoComponent.todos.get(i);
            if (todo.act(deltaTime)) {
                todoComponent.todos.removeIndex(i);
                i--;
            }
        }
    }
}
