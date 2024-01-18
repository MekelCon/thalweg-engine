package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.SequenceTaskComponent;

public class SequenceTask extends Task {

    private static final Class<SequenceTaskComponent> COMPONENT = SequenceTaskComponent.class;
    private static final Family FAMILY = Family.all(WorkingFlag.class, COMPONENT).get();
    private final ComponentMapper<SequenceTaskComponent> cm;

    public SequenceTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var sequenceTaskComponent = cm.get(entity);
        if (sequenceTaskComponent.executor == null
                || sequenceTaskComponent.executor.getComponents().size() == 0) {
            if (sequenceTaskComponent.executor != null) {
                getEngine().removeEntity(sequenceTaskComponent.executor);
            }
            if (sequenceTaskComponent.currentIndex < sequenceTaskComponent.components.size) {
                sequenceTaskComponent.executor = getEngine().createEntity();
                sequenceTaskComponent.executor
                        .add(sequenceTaskComponent.components
                                .get(sequenceTaskComponent.currentIndex))
                        .add(getEngine().createComponent(WorkingFlag.class));
                sequenceTaskComponent.currentIndex = sequenceTaskComponent.currentIndex + 1;
                getEngine().addEntity(sequenceTaskComponent.executor);
            } else {
                sequenceTaskComponent.executor = null;
                sequenceTaskComponent.currentIndex = 0;
                entity.removeAll();
                getEngine().removeEntity(entity);
            }
        }
    }
}
