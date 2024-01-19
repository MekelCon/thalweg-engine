package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.TaskBuilder;
import fr.thalweg.gen.engine.model.TaskData;

public abstract class TriggerSystem extends IteratingSystem {
    public TriggerSystem(Family family) {
        super(family);
    }

    public TriggerSystem(Family family, int priority) {
        super(family, priority);
    }

    protected void createEntityForTriggered(TaskData todo) {
        if (todo != null)
            getEngine().addEntity(getEngine().createEntity()
                    .add(TaskBuilder.build(getEngine(),
                            todo,
                            ThalwegGame.INSTANCE.getRoot()))
                    .add(getEngine().createComponent(WorkingFlag.class)));
    }
}
