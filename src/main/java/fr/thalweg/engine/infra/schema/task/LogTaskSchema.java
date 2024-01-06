package fr.thalweg.engine.infra.schema.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogTaskSchema extends AbstractTaskSchema {
    @JsonProperty(required = true)
    public String message;

}
