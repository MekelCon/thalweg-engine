package fr.thalweg.engine.infra.data.task;

import com.badlogic.ashley.core.Component;

public abstract class TaskData implements Component {

    public TaskTypeEnumData type;

    @SuppressWarnings("unchecked")
    public <T extends TaskData> T copy() {
        return (T) TaskClone.INSTANCE.copy(this);
    }

}

