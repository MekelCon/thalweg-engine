package fr.thalweg.engine.infra.data.task;

public enum TaskTypeEnumData {

    LOG("LOG", LogTaskData.class),

    PARALLEL("PARALLEL", ParallelTaskData.class),

    PLAY_TRANSITION("PLAY_TRANSITION", PlayTransitionTaskData.class),

    SEQUENCE("SEQUENCE", SequenceTaskData.class),

    SET_CURSOR("SET_CURSOR", SetCursorTaskData.class),

    SET_MOUSE_LABEL("SET_MOUSE_LABEL", SetMouseLabelTaskData.class),

    WAIT("WAIT", WaitTaskData.class);

    public final String value;

    public final Class<? extends TaskData> target;

    TaskTypeEnumData(String value, Class<? extends TaskData> target) {
        this.value = value;
        this.target = target;
    }
}

