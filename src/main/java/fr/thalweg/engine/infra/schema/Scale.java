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
    private float x = 1.0F;
    @JsonProperty
    private float y = 1.0F;

}
