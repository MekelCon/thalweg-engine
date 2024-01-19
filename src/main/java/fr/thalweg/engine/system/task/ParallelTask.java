package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.ParallelTaskComponent;

public class ParallelTask extends Task {

    private static final Class<ParallelTaskComponent> COMPONENT = ParallelTaskComponent.class;
    private final ComponentMapper<ParallelTaskComponent> cm;

    public ParallelTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var parallelTaskComponent = cm.get(entity);
        if (!parallelTaskComponent._started) { // The task is not started
            parallelTaskComponent._started = true;
            parallelTaskComponent.executors = new Array<>(parallelTaskComponent.components.size);
            for (Component component : parallelTaskComponent.components) {
                Entity executor = getEngine().createEntity();
                executor.add(getEngine().createComponent(WorkingFlag.class));
                executor.add(component);
                getEngine().addEntity(executor);
                parallelTaskComponent.executors.add(executor);
            }

        } else if (parallelTaskComponent.executors.size != 0) { // Subtask started but not ended
            for (int i = 0; i < parallelTaskComponent.executors.size; i++) {
                if (parallelTaskComponent.executors.get(i).getComponents().size() == 0) {
                    getEngine().removeEntity(parallelTaskComponent.executors.get(i));
                    parallelTaskComponent.executors.removeIndex(i);
                }
            }

        } else { // All subtask ended

            entity.remove(WorkingFlag.class);
            getEngine().removeEntity(entity);
        }
    }
}
