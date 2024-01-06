package fr.thalweg.engine.infra.schema.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeCursorTaskSchema extends AbstractTaskSchema {
    @JsonProperty(required = true)
    public String cursor;
    @JsonProperty
    public Integer xHotspot = 0;
    @JsonProperty
    public Integer yHotspot = 0;
}

