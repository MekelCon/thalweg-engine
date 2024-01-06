package fr.thalweg.engine.infra.schema.trigger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TriggerTypeEnum {

    MOUSE_ENTER("MOUSE_ENTER"),
    MOUSE_LEAVE("MOUSE_LEAVE");

    private final String value;

    TriggerTypeEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TriggerTypeEnum fromValue(String value) {
        for (TriggerTypeEnum b : TriggerTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

