package fr.thalweg.engine.infra.data.trigger;

import lombok.Getter;

@Getter
public enum TriggerTypeEnumData {

    AUTO("AUTO", AutoTriggerData.class),

    MOUSE_ENTER("MOUSE_ENTER", MouseEnterTriggerData.class),

    MOUSE_LEAVE("MOUSE_LEAVE", MouseLeaveTriggerData.class);

    private final String value;

    private final Class<? extends TriggerData> target;

    TriggerTypeEnumData(String value, Class<? extends TriggerData> target) {
        this.value = value;
        this.target = target;
    }
}

