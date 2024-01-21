package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public abstract class TaskComp implements Component, Pool.Poolable {
    public TaskTypeEnumData type;

    public TaskComp() {
        this.type = TaskTypeEnumData.forTarget(this.getClass());
    }

    @Override
    public void reset() {
    }

    public void build() {
    }
}
