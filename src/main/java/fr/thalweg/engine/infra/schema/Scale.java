package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Scale {
    @JsonProperty
    @Builder.Default
    public float x = 1.0F;
    @JsonProperty
    @Builder.Default
    public float y = 1.0F;

}
