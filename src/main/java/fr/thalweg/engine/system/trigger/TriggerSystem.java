package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.TaskBuilder;
import fr.thalweg.engine.component.task.TaskComp;

public abstract class TriggerSystem extends IteratingSystem {
    public TriggerSystem(Family family) {
        super(family);
    }

    public TriggerSystem(Family family, int priority) {
        super(family, priority);
    }

    protected void createEntityForTriggered(TaskComp todo) {
        if (todo != null) {
            TaskComp result = TaskBuilder.build(getEngine(),
                    todo,
                    ThalwegGame.INSTANCE.getRoot());
            getEngine().addEntity(getEngine().createEntity()
                    .add(result)
                    .add(getEngine().createComponent(WorkingFlag.class)));
        }
    }
}
