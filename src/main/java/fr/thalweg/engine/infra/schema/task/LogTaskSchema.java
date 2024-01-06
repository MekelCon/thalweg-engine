package fr.thalweg.engine.infra.schema.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LogTaskSchema extends AbstractTaskSchema {
    @JsonProperty(required = true)
    public String message;

}
