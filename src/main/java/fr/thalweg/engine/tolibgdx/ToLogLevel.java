package fr.thalweg.engine.tolibgdx;

import com.badlogic.gdx.Application;
import fr.thalweg.engine.gen.GdxConfigurationSchema;

public class ToLogLevel {
    public static int from(GdxConfigurationSchema.LogLevel logLevel) {
        return switch (logLevel) {
            case DEBUG -> Application.LOG_DEBUG;
            case INFO -> Application.LOG_INFO;
            case ERROR -> Application.LOG_ERROR;
            case NONE -> Application.LOG_NONE;
        };
    }
}