package fr.thalweg.engine.component.task;

import com.badlogic.gdx.utils.Array;

public abstract class EmployeeTaskComponent implements TaskComponent {
    public Array<TaskComponent> components = new Array<>();

    @Override
    public void reset() {
        for (TaskComponent task : components) {
            task.reset();
        }
        components.clear();
    }
}
