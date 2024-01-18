package fr.thalweg.engine.system.task.overtime;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.WaitTaskComponent;

public class WaitTask extends OverTimeTask {

    private static final Class<WaitTaskComponent> COMPONENT = WaitTaskComponent.class;
    private static final Family FAMILY = Family.all(COMPONENT, WorkingFlag.class).get();
    private final ComponentMapper<WaitTaskComponent> cm;

    public WaitTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected float getDuration(Entity entity) {
        return cm.get(entity).data.getDuration();
    }

    @Override
    protected void update(Entity entity, float percent) {
        // Nothing to do, we just wait for the 100%
    }

}
