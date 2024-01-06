package fr.thalweg.engine.infra.schema.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskTypeEnum {

    CHANGE_CURSOR("CHANGE_CURSOR"),
    LOG("LOG");

    private final String value;

    TaskTypeEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TaskTypeEnum fromValue(String value) {
        for (TaskTypeEnum b : TaskTypeEnum.values()) {
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

