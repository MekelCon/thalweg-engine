package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Scale {
    @JsonProperty
    public float x = 1.0F;
    @JsonProperty
    public float y = 1.0F;

}
