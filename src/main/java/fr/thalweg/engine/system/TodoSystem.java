package fr.thalweg.engine.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.TodoComponent;
import fr.thalweg.engine.component.trigger.todo.Todo;

public class TodoSystem extends IteratingSystem {

    private final Array<Todo> doingQueue;
    private final ComponentMapper<TodoComponent> todoComponentMapper;

    public TodoSystem() {
        super(Family.all(TodoComponent.class).get());
        this.doingQueue = new Array<>();
        this.todoComponentMapper = ComponentMapper.getFor(TodoComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for (int i = 0; i < doingQueue.size; i++) {
            Todo todo = doingQueue.get(i);
            if (todo.doing(deltaTime)) {
                doingQueue.removeIndex(i);
                i--;
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TodoComponent todoComponent = todoComponentMapper.get(entity);
        doingQueue.addAll(todoComponent.todos);
        entity.remove(TodoComponent.class);
    }
}
