package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.TaskComp;
import fr.thalweg.engine.component.trigger.AutoTriggerComponent;

public class AutoTriggerSystem extends TriggerSystem {
    private final ComponentMapper<AutoTriggerComponent> am;

    public AutoTriggerSystem() {
        super(Family.all(AutoTriggerComponent.class).get());
        this.am = ComponentMapper.getFor(AutoTriggerComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var autoTriggerComponent = am.get(entity);
        createEntityForTriggered(autoTriggerComponent.todo);
        entity.remove(AutoTriggerComponent.class);
        if (entity.getComponents().size() == 0) {
            getEngine().removeEntity(entity);
        }
    }

    @Override
    protected void createEntityForTriggered(TaskComp todo) {
        if (todo != null) {
            // In case of auto trigger the task will be "Working" only once
            // so we don't need to "copy" them
            todo.build();
            getEngine().addEntity(getEngine().createEntity()
                    .add(todo)
                    .add(getEngine().createComponent(WorkingFlag.class)));
        }
    }
}