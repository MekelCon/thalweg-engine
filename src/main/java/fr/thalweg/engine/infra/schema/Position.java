package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


/**
 * The position in the virtual screen coordinate system
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Position {
    @JsonProperty
    @Builder.Default
    public float x = 0.0F;
    @JsonProperty
    @Builder.Default
    public float y = 0.0F;
    @JsonProperty("z")
    @Builder.Default
    public int z = 0;
}
