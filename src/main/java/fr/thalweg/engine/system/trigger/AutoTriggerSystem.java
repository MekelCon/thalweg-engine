package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.TaskBuilder;
import fr.thalweg.engine.component.trigger.AutoTriggerComponent;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.gen.engine.model.TaskData;
import lombok.SneakyThrows;

public class AutoTriggerSystem extends IteratingSystem {
    private final ComponentMapper<AutoTriggerComponent> am;

    public AutoTriggerSystem() {
        super(Family.all(AutoTriggerComponent.class).get());
        this.am = ComponentMapper.getFor(AutoTriggerComponent.class);
    }

    @SneakyThrows
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var autoTriggerComponent = am.get(entity);
        var mapper = Reader.getInstance().getJsonMapper();
        if (autoTriggerComponent.todo != null) {
            getEngine().addEntity(getEngine().createEntity()
                    .add(TaskBuilder.build(getEngine(),
                            mapper.convertValue(autoTriggerComponent.todo, TaskData.class),
                            ThalwegGame.INSTANCE.getRoot()))
                    .add(getEngine().createComponent(WorkingFlag.class)));
        }
        entity.remove(AutoTriggerComponent.class);
        if (entity.getComponents().size() == 0) {
            getEngine().removeEntity(entity);
        }
    }
}