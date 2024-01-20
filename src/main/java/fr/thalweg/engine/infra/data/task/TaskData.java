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

    @SuppressWarnings("unchecked")
    public <T extends TaskData> T copy() {
        return (T) TaskClone.INSTANCE.copy(this);
    }
}

