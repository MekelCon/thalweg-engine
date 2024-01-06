package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The position in the virtual screen coordinate system
 */
@Getter
@Setter
@NoArgsConstructor
public class Position {
    @JsonProperty
    private float x = 0.0F;
    @JsonProperty
    private float y = 0.0F;
    @JsonProperty("z")
    private int z = 0;
}
