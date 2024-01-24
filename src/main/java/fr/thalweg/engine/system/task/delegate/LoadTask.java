package fr.thalweg.engine.system.task.delegate;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.LoadTaskComp;
import fr.thalweg.engine.system.task.Task;

public class LoadTask extends Task {

    private static final Class<LoadTaskComp> COMPONENT = LoadTaskComp.class;
    private final ComponentMapper<LoadTaskComp> cm;

    public LoadTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var loadTaskComp = cm.get(entity);
        if (loadTaskComp._executor == null) { // The task is not started
            loadTaskComp._executor = getEngine().createEntity()
                    .add(getEngine().createComponent(WorkingFlag.class))
                    .add(loadTaskComp._todo);
            getEngine().addEntity(loadTaskComp._executor);
        } else if (loadTaskComp._executor.getComponents().size() == 0) { // Subtask ended
            entity.remove(WorkingFlag.class);
            getEngine().removeEntity(entity);
        }
    }
}
