package fr.thalweg.engine.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.TaskTodoComp;
import fr.thalweg.engine.infra.schema.task.AbstractTask;

public class TaskTodoSystem extends IteratingSystem {

    private final Array<AbstractTask> workingQueue;
    private final ComponentMapper<TaskTodoComp> todoComponentMapper;

    public TaskTodoSystem() {
        super(Family.all(TaskTodoComp.class).get());
        this.workingQueue = new Array<>();
        this.todoComponentMapper = ComponentMapper.getFor(TaskTodoComp.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for (int i = 0; i < workingQueue.size; i++) {
            AbstractTask todo = workingQueue.get(i);
            if (todo.work(deltaTime)) {
                workingQueue.removeIndex(i);
                i--;
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TaskTodoComp taskTodoComp = todoComponentMapper.get(entity);
        workingQueue.addAll(taskTodoComp.todos);
        entity.remove(TaskTodoComp.class);
    }
}
