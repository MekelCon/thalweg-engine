package fr.thalweg.engine.component.task;

import fr.thalweg.gen.engine.model.OverTimeTaskData;

public class WaitTaskComponent implements TaskComponent {

    public OverTimeTaskData data;

    @Override
    public void reset() {
        data = null;
    }
}
