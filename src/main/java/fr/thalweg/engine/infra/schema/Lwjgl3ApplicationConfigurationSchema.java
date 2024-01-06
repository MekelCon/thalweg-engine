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
public class Lwjgl3ApplicationConfigurationSchema {

    @JsonProperty("title")
    public String title;
    @JsonProperty("windowed")
    public Windowed windowed;
    @JsonProperty("useVSync")
    public boolean useVSync = true;
    @JsonProperty("foregroundFPS")
    public int foregroundFPS = 60;
}
