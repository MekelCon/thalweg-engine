package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.trigger.AutoTriggerComponent;
import lombok.SneakyThrows;

public class AutoTriggerSystem extends TriggerSystem {
    private final ComponentMapper<AutoTriggerComponent> am;

    public AutoTriggerSystem() {
        super(Family.all(AutoTriggerComponent.class).get());
        this.am = ComponentMapper.getFor(AutoTriggerComponent.class);
    }

    @SneakyThrows
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var autoTriggerComponent = am.get(entity);
        createEntityForTriggered(autoTriggerComponent.todo);
        entity.remove(AutoTriggerComponent.class);
        if (entity.getComponents().size() == 0) {
            getEngine().removeEntity(entity);
        }
    }
}