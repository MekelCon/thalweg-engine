package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.thalweg.engine.infra.schema.trigger.TriggerSchema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ThalwegActorSchema {
    @JsonProperty
    @JsonPropertyDescription("The position in the virtual screen coordinate system")
    public Position position;
    @JsonProperty
    public Scale scale;
    @JsonProperty
    public String texture;
    @JsonProperty
    @Builder.Default
    public List<Float> vertices = new ArrayList<>();
    @JsonProperty
    @Builder.Default
    public List<TriggerSchema> triggers = new ArrayList<>();
}
