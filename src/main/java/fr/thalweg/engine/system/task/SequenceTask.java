package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.SequenceTaskComponent;

public class SequenceTask extends Task {

    private static final Class<SequenceTaskComponent> COMPONENT = SequenceTaskComponent.class;
    private final ComponentMapper<SequenceTaskComponent> cm;

    public SequenceTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var sequenceTaskComponent = cm.get(entity);
        if (sequenceTaskComponent._executor == null
                || sequenceTaskComponent._executor.getComponents().size() == 0) {
            if (sequenceTaskComponent._executor != null) {
                getEngine().removeEntity(sequenceTaskComponent._executor);
            }
            if (sequenceTaskComponent._currentIndex < sequenceTaskComponent.components.size) {
                sequenceTaskComponent._executor = getEngine().createEntity();
                sequenceTaskComponent._executor
                        .add(sequenceTaskComponent.components
                                .get(sequenceTaskComponent._currentIndex))
                        .add(getEngine().createComponent(WorkingFlag.class));
                sequenceTaskComponent._currentIndex = sequenceTaskComponent._currentIndex + 1;
                getEngine().addEntity(sequenceTaskComponent._executor);
            } else {
                sequenceTaskComponent._executor = null;
                sequenceTaskComponent._currentIndex = 0;
                entity.removeAll();
                getEngine().removeEntity(entity);
            }
        }
    }
}
