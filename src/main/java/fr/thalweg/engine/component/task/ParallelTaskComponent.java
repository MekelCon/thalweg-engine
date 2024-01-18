package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class ParallelTaskComponent extends EmployeeTaskComponent {
    public Array<Entity> executors = new Array<>();

    @Override
    public void reset() {
        super.reset();
        if (executors != null) {
            executors.clear();
        }
    }
}
