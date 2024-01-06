package fr.thalweg.engine.infra.schema.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskSchemaTypeEnum {

    CHANGE_CURSOR("CHANGE_CURSOR"),
    LOG("LOG");

    private final String value;

    TaskSchemaTypeEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TaskSchemaTypeEnum fromValue(String value) {
        for (TaskSchemaTypeEnum b : TaskSchemaTypeEnum.values()) {
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

