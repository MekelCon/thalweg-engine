package fr.thalweg.engine.infra.schema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class GdxConfigurationSchema {

    @JsonProperty("logLevel")
    private LogLevel logLevel = LogLevel.fromValue("INFO");

    public enum LogLevel {

        DEBUG("DEBUG"),
        INFO("INFO"),
        ERROR("ERROR"),
        NONE("NONE");
        private final static Map<String, LogLevel> CONSTANTS = new HashMap<String, LogLevel>();

        static {
            for (LogLevel c : values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private final String value;

        LogLevel(String value) {
            this.value = value;
        }

        @JsonCreator
        public static LogLevel fromValue(String value) {
            LogLevel constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }
}
