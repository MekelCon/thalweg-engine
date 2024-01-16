package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.trigger.AutoTriggerComponent;

public class AutoTriggerSystem extends IteratingSystem {
    private final ComponentMapper<AutoTriggerComponent> am;

    public AutoTriggerSystem() {
        super(Family.all(AutoTriggerComponent.class).get());
        this.am = ComponentMapper.getFor(AutoTriggerComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var autoTriggerComponent = am.get(entity);
        getEngine().addEntity(getEngine().createEntity()
                .add(autoTriggerComponent.todo)
                .add(getEngine().createComponent(WorkingFlag.class)));
        entity.remove(AutoTriggerComponent.class);
        if (entity.getComponents().size() == 0) {
            getEngine().removeEntity(entity);
        }
    }
}