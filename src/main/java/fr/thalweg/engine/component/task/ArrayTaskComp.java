package fr.thalweg.engine.component.task;

import com.badlogic.gdx.utils.Array;

public abstract class ArrayTaskComp extends TaskComp {
    public Array<TaskComp> todos = new Array<>();

    @Override
    public void reset() {
        super.reset();
        todos.clear();
    }
}
