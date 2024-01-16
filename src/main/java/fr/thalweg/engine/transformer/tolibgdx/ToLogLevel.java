package fr.thalweg.engine.transformer.tolibgdx;

import com.badlogic.gdx.Application;
import fr.thalweg.gen.engine.model.LogLevelEnumData;

public class ToLogLevel {
    public static int from(LogLevelEnumData logLevel) {
        return switch (logLevel) {
            case DEBUG -> Application.LOG_DEBUG;
            case INFO -> Application.LOG_INFO;
            case ERROR -> Application.LOG_ERROR;
            case NONE -> Application.LOG_NONE;
        };
    }
}
