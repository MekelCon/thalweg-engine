package fr.thalweg.engine.system.task;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.component.flag.WorkingFlag;
import fr.thalweg.engine.component.task.ParallelTaskComp;
import fr.thalweg.engine.component.task.TaskComp;
import fr.thalweg.engine.component.task.TaskTypeEnumData;

import java.util.Iterator;

public class ParallelTask extends Task {

    private static final Class<ParallelTaskComp> COMPONENT = ParallelTaskComp.class;
    private final ComponentMapper<ParallelTaskComp> cm;

    public ParallelTask() {
        super(COMPONENT);
        this.cm = ComponentMapper.getFor(COMPONENT);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var parallelTaskComponent = cm.get(entity);
        if (!parallelTaskComponent._started) { // The task is not started
            parallelTaskComponent._started = true;
            parallelTaskComponent._executors = new Array<>(parallelTaskComponent.todos.size);
            for (TaskComp component : parallelTaskComponent.todos) {
                addAllParallel(parallelTaskComponent._executors, component);
            }

        } else if (parallelTaskComponent._executors.size != 0) { // Subtask started but not ended
            for (Iterator<Entity> iterator = parallelTaskComponent._executors.iterator(); iterator.hasNext(); ) {
                var executor = iterator.next();
                if (executor.getComponents().size() == 0) {
                    getEngine().removeEntity(executor);
                    iterator.remove();
                }
            }
            for (int i = 0; i < parallelTaskComponent._executors.size; i++) {
                if (parallelTaskComponent._executors.get(i).getComponents().size() == 0) {
                    getEngine().removeEntity(parallelTaskComponent._executors.get(i));
                    parallelTaskComponent._executors.removeIndex(i);
                }
            }
        } else { // All subtask ended
            entity.remove(WorkingFlag.class);
            getEngine().removeEntity(entity);
        }
    }

    private void addAllParallel(Array<Entity> executors, TaskComp component) {
        if (TaskTypeEnumData.PARALLEL == component.type) {
            for (TaskComp todo : ((ParallelTaskComp) component).todos) {
                addAllParallel(executors, todo);
            }
        } else {
            Entity executor = getEngine().createEntity();
            executor
                    .add(getEngine().createComponent(WorkingFlag.class))
                    .add(component);
            getEngine().addEntity(executor);
            executors.add(executor);
        }
    }
}
