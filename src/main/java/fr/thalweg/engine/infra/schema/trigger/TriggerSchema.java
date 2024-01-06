/*
 * Fake API
 * Just here to generate model of the quercus engine
 *
 * The version of the OpenAPI document: 2022.08.15
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.thalweg.engine.infra.schema.trigger;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.thalweg.engine.infra.schema.task.AbstractTaskSchema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TriggerSchema {
    @JsonProperty
    private TriggerTypeEnum type;
    @JsonProperty
    @Builder.Default
    private List<AbstractTaskSchema> todos = new ArrayList<>();


}

