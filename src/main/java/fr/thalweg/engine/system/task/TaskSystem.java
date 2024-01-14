package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import fr.thalweg.engine.component.task.TaskComponent;

public class TaskSystem extends IteratingSystem {

    private final ComponentMapper<TaskComponent> tm;

    public TaskSystem() {
        super(Family.all(TaskComponent.class).get());
        this.tm = ComponentMapper.getFor(TaskComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var taskComponent = tm.get(entity);
        if (taskComponent.task.work(entity, deltaTime)) {
            entity.remove(TaskComponent.class);
        }
    }
}
