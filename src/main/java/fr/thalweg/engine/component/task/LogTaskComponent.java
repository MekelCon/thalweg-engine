package fr.thalweg.engine.component.task;

import fr.thalweg.gen.engine.model.LogTaskData;

public class LogTaskComponent implements TaskComponent {

    public LogTaskData data;

    @Override
    public void reset() {
        data = null;
    }
}
