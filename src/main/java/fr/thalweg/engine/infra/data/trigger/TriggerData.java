package fr.thalweg.engine.infra.data.trigger;

import fr.thalweg.engine.component.task.TaskComp;

import java.util.Arrays;

public abstract class TriggerData {

    public final TriggerTypeEnumData type;
    public TaskComp todo;

    public TriggerData() {
        type = Arrays.stream(TriggerTypeEnumData.values())
                .filter(triggerType -> triggerType.getTarget().equals(this.getClass()))
                .findFirst()
                .orElseThrow();
    }
}

