package fr.thalweg.engine.system.trigger;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import fr.thalweg.engine.ThalwegPooledEngine;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.TaskComp;

public abstract class TriggerSystem extends IteratingSystem {

    private ThalwegPooledEngine _engine;

    public TriggerSystem(Family family) {
        super(family);
    }

    public TriggerSystem(Family family, int priority) {
        super(family, priority);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        // Onz time cast to be able to use
        _engine = (ThalwegPooledEngine) engine;
    }

    protected void createEntityForTriggered(TaskComp todo) {
        if (todo != null) {
            TaskComp result = _engine.createTaskComponent(todo);
            getEngine().addEntity(getEngine().createEntity()
                    .add(result)
                    .add(getEngine().createComponent(WorkingFlag.class)));
        }
    }
}
