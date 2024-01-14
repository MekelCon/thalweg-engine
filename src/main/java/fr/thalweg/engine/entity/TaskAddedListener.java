package fr.thalweg.engine.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import fr.thalweg.engine.component.task.TaskComponent;

public class TaskAddedListener implements EntityListener {

    private final ComponentMapper<TaskComponent> tm;

    public TaskAddedListener() {
        this.tm = ComponentMapper.getFor(TaskComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        var taskComponent = tm.get(entity);
        taskComponent.task.added();
    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}
