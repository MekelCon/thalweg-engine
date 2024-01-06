package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ThalwegGameScreenSchema {
    @JsonProperty
    private String name;
    @JsonProperty
    private List<ThalwegActorSchema> actors = new ArrayList<ThalwegActorSchema>();
}
