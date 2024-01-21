package fr.thalweg.engine;

import com.badlogic.ashley.core.PooledEngine;
import fr.thalweg.engine.component.task.TaskComp;

public class ThalwegPooledEngine extends PooledEngine {

    public ThalwegPooledEngine(int entityPoolInitialSize, int entityPoolMaxSize, int componentPoolInitialSize, int componentPoolMaxSize) {
        super(entityPoolInitialSize, entityPoolMaxSize, componentPoolInitialSize, componentPoolMaxSize);
    }

    @SuppressWarnings("unchecked")
    public <T extends TaskComp> T createTaskComponent(T source) {
        T result = (T) super.createComponent(source.getClass());
        TaskUpdater.INSTANCE.update(source, result);
        result.build();
        return result;
    }
}
