package fr.thalweg.engine.infra.data.task;

public abstract class TaskData {

    public TaskTypeEnumData type;

    @SuppressWarnings("unchecked")
    public <T extends TaskData> T copy() {
        return (T) TaskClone.INSTANCE.copy(this);
    }
}

