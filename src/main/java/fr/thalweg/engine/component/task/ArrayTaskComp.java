package fr.thalweg.engine.component.task;

import com.badlogic.gdx.utils.Array;

public abstract class ArrayTaskComp extends TaskComp {
    public Array<TaskComp> todos;

    @Override
    public void reset() {
        super.reset();
        todos.clear();
    }

    @Override
    public void build() {
        for (TaskComp todo : todos) {
            todo.build();
        }
    }
}
