package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.WaitTaskComponent;

public class WaitTask extends OverTimeTask<WaitTaskComponent> {

    private static final Class<WaitTaskComponent> COMPONENT = WaitTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();

    public WaitTask() {
        super(FAMILY, COMPONENT);
    }

    @Override
    protected void update(Entity entity, float percent) {
        // Nothing to do, we just wait for the 100%
    }

}
