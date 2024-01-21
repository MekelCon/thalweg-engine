package fr.thalweg.engine.component.task;

public class SetMouseLabelTaskComp extends OverTimeTaskComp {

    public String label;

    @Override
    public void reset() {
        super.reset();
        label = null;
    }
}
