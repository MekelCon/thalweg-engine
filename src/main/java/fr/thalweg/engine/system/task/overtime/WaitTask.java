package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.task.WaitTaskComponent;

public class WaitTask extends OverTimeTask {

    private static final Class<WaitTaskComponent> COMPONENT = WaitTaskComponent.class;

    public WaitTask() {
        super(COMPONENT);
    }

    @Override
    protected void update(Entity entity, float percent) {
        // Nothing to do, we just wait for the 100%
    }

}
