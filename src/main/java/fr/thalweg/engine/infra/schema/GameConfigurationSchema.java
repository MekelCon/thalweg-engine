package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameConfigurationSchema {

    @JsonProperty
    private boolean debug = false;
    @JsonProperty
    @JsonPropertyDescription("LibGDX configuration")
    private GdxConfigurationSchema gdx;
    @JsonProperty("lwjgl3ApplicationConfiguration")
    @JsonPropertyDescription("Lw3jgl application configuration")
    private Lwjgl3ApplicationConfigurationSchema lwjgl3ApplicationConfiguration;
    @JsonProperty("startScreen")
    private String startScreen;
    @JsonProperty("world")
    private World world;
}
