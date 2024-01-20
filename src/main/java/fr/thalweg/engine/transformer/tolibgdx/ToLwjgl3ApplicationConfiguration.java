package fr.thalweg.engine.transformer.tolibgdx;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import fr.thalweg.engine.infra.data.Lwjgl3ApplicationConfigData;

public class ToLwjgl3ApplicationConfiguration {

    public static Lwjgl3ApplicationConfiguration from(Lwjgl3ApplicationConfigData source) {
        var config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(source.title);
        config.useVsync(source.useVSync);
        config.setForegroundFPS(source.foregroundFPS);
        if (source.windowed != null) {
            config.setWindowedMode(
                    source.windowed.width,
                    source.windowed.height);
        } else {
            Graphics.DisplayMode primaryMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
            config.setFullscreenMode(primaryMode);
        }
        return config;
    }
}
