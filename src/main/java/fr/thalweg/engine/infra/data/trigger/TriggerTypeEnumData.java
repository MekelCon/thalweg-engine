package fr.thalweg.engine.infra.data.trigger;

import lombok.Getter;

@Getter
public enum TriggerTypeEnumData {

    AUTO("AUTO", AutoTriggerData.class),

    MOUSEENTER("MOUSEENTER", MouseEnterTriggerData.class),

    MOUSELEAVE("MOUSELEAVE", MouseLeaveTriggerData.class);

    private final String value;

    private final Class<? extends TriggerData> target;

    TriggerTypeEnumData(String value, Class<? extends TriggerData> target) {
        this.value = value;
        this.target = target;
    }
}

