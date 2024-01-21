package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.component.task.WaitTaskComp;

public class WaitTask extends OverTimeTask {

    private static final Class<WaitTaskComp> COMPONENT = WaitTaskComp.class;

    public WaitTask() {
        super(COMPONENT);
    }

    @Override
    protected void update(Entity entity, float percent) {
        // Nothing to do, we just wait for the 100%
    }

}
