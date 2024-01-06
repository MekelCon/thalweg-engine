package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class GameConfigurationSchema {
    @JsonProperty
    public boolean debug = false;
    @JsonProperty
    @JsonPropertyDescription("LibGDX configuration")
    public GdxConfigurationSchema gdx;
    @JsonProperty("lwjgl3ApplicationConfiguration")
    @JsonPropertyDescription("Lw3jgl application configuration")
    public Lwjgl3ApplicationConfigurationSchema lwjgl3ApplicationConfiguration;
    @JsonProperty("startScreen")
    public String startScreen;
    @JsonProperty("world")
    public World world;
}
