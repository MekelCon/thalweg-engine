package fr.thalweg.engine.transformer.tolibgdx;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import fr.thalweg.engine.infra.data.Lwjgl3ApplicationConfigData;

public class ToLwjgl3ApplicationConfiguration {

    public static Lwjgl3ApplicationConfiguration from(Lwjgl3ApplicationConfigData source) {
        var config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(source.getTitle());
        config.useVsync(source.isUseVSync());
        config.setForegroundFPS(source.getForegroundFPS());
        if (source.getWindowed() != null) {
            config.setWindowedMode(
                    source.getWindowed().getWidth(),
                    source.getWindowed().getHeight());
        } else {
            Graphics.DisplayMode primaryMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
            config.setFullscreenMode(primaryMode);
        }
        return config;
    }
}
