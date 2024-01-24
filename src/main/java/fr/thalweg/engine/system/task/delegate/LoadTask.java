package fr.thalweg.engine.system.task.delegate;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.LoadTaskComp;
import fr.thalweg.engine.system.task.Task;

public class LoadTask extends Task {

    private static final Class<LoadTaskComp> COMPONENT = LoadTaskComp.class;
    private final ComponentMapper<LoadTaskComp> cm;

    public LoadTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var loadTaskComp = cm.get(entity);
        if (loadTaskComp._executor == null) {
            loadTaskComp._executor = getEngine().createEntity();
            getEngine().addEntity(loadTaskComp._executor
                    .add(ThalwegGame.INSTANCE.getEcsEngine().createTaskComponent(loadTaskComp._todo))
                    .add(getEngine().createComponent(WorkingFlag.class)));
        } else if (loadTaskComp._executor.getComponents().size() == 0) {
            getEngine().removeEntity(loadTaskComp._executor);
            loadTaskComp._executor = null;
            entity.removeAll();
            getEngine().removeEntity(entity);
        }
    }
}
