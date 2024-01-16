package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.ParallelTaskComponent;


public class ParallelTask extends Task {

    private static final Class<ParallelTaskComponent> COMPONENT = ParallelTaskComponent.class;
    private static final Family FAMILY = Family.all(WorkingFlag.class, COMPONENT).get();
    private final ComponentMapper<ParallelTaskComponent> cm;


    public ParallelTask() {
        super(FAMILY);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var parallelTaskComponent = cm.get(entity);
        if (parallelTaskComponent.executors == null) {
            parallelTaskComponent.executors = new Array<>(parallelTaskComponent.components.size);
            for (Component component : parallelTaskComponent.components) {
                Entity executor = getEngine().createEntity();
                executor.add(getEngine().createComponent(WorkingFlag.class));
                executor.add(component);
                getEngine().addEntity(executor);
                parallelTaskComponent.executors.add(executor);
            }
        } else if (parallelTaskComponent.executors.size != 0) {
            for (int i = 0; i < parallelTaskComponent.executors.size; i++) {
                if (parallelTaskComponent.executors.get(i).getComponents().size() == 0) {
                    getEngine().removeEntity(parallelTaskComponent.executors.get(i));
                    parallelTaskComponent.executors.removeIndex(i);
                }
            }
        } else {
            entity.remove(WorkingFlag.class);
            parallelTaskComponent.executors = null;
            getEngine().removeEntity(entity);
        }
    }
}
