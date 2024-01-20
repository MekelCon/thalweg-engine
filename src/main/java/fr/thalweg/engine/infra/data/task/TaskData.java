package fr.thalweg.engine.infra.data.task;

import java.util.Arrays;

public abstract class TaskData {

    public final TaskTypeEnumData type;

    public TaskData() {
        type = Arrays.stream(TaskTypeEnumData.values())
                .filter(taskType -> taskType.target.equals(this.getClass()))
                .findFirst()
                .orElseThrow();
    }

    public abstract TaskData copy();
}

