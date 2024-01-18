package fr.thalweg.engine.component.task;

import com.badlogic.ashley.core.Entity;

public class SequenceTaskComponent extends EmployeeTaskComponent {
    public int currentIndex;
    public Entity executor;

    @Override
    public void reset() {
        super.reset();
        currentIndex = 0;
        executor = null;
    }
}
