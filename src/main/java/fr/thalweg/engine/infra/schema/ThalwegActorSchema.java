package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.thalweg.engine.infra.schema.trigger.TriggerSchema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ThalwegActorSchema {
    @JsonProperty
    @JsonPropertyDescription("The position in the virtual screen coordinate system")
    public Position position;
    @JsonProperty
    public Scale scale;
    @JsonProperty
    public String texture;
    @JsonProperty
    public List<Float> vertices = new ArrayList<>();
    @JsonProperty
    public List<TriggerSchema> triggers = new ArrayList<>();
}
