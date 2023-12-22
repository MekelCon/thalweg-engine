package fr.thalweg.engine.tolibgdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import fr.thalweg.engine.gen.Lwjgl3ApplicationConfigurationSchema;

public class ToLwjgl3ApplicationConfiguration {

    public static Lwjgl3ApplicationConfiguration from(Lwjgl3ApplicationConfigurationSchema source) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(source.getTitle());
        config.setWindowedMode(
                source.getWindowedWidth(),
                source.getWindowedHeight());
        config.useVsync(source.isUseVSync());
        config.setForegroundFPS(source.getForegroundFPS());
        return config;
    }
}
