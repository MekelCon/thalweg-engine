package fr.thalweg.engine.component.task;

public class LogTaskComp extends TaskComp {

    public String message;

    @Override
    public void reset() {
        super.reset();
        message = null;
    }
}
