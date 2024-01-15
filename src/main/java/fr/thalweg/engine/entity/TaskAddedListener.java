package fr.thalweg.engine.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.TaskComponent;

public class TaskAddedListener implements EntityListener {

    public static Family LISTENING = Family.all(TaskComponent.class).get();
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
