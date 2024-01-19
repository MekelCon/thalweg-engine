package fr.thalweg.engine.component.task;

import fr.thalweg.gen.engine.model.SetMouseLabelTaskData;

public class SetMouseLabelTaskComponent implements TaskComponent {

    public SetMouseLabelTaskData data;

    @Override
    public void reset() {
        data = null;
    }
}
