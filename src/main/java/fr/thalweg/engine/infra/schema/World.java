package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class World {
    @JsonProperty
    public int height;
    @JsonProperty
    public int width;
}
