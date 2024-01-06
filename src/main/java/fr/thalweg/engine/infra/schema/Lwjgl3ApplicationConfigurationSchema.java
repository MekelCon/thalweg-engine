package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lwjgl3ApplicationConfigurationSchema {

    @JsonProperty("title")
    private String title;
    @JsonProperty("windowed")
    private Windowed windowed;
    @JsonProperty("useVSync")
    private boolean useVSync = true;
    @JsonProperty("foregroundFPS")
    private int foregroundFPS = 60;
}
