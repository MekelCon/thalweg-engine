package fr.thalweg.engine.component.task;


public enum TaskTypeEnumData {

    LOAD("LOAD", LoadTaskComp.class),
    LOG("LOG", LogTaskComp.class),
    PARALLEL("PARALLEL", ParallelTaskComp.class),
    PLAY_TRANSITION("PLAY_TRANSITION", PlayTransitionTaskComp.class),
    SEQUENCE("SEQUENCE", SequenceTaskComp.class),
    SET_CURSOR("SET_CURSOR", SetCursorTaskComp.class),
    SET_MOUSE_LABEL("SET_MOUSE_LABEL", SetMouseLabelTaskComp.class),
    WAIT("WAIT", WaitTaskComp.class);

    public final String value;

    public final Class<? extends TaskComp> target;

    TaskTypeEnumData(String value, Class<? extends TaskComp> target) {
        this.value = value;
        this.target = target;
    }
}

