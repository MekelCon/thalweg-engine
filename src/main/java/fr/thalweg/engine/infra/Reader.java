package fr.thalweg.engine.infra;

import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.SneakyThrows;

public class Reader {

    @Getter
    private static final Reader instance = new Reader();

    @Getter
    private final ObjectMapper jsonMapper;

    private final ObjectMapper yamlMapper;

    private Reader() {
        jsonMapper = new ObjectMapper();
        yamlMapper = new ObjectMapper(new YAMLFactory());
    }

    @SneakyThrows
    public <T> T read(FileHandle fileHandle, Class<T> clazz) {
        if ("yaml".equals(fileHandle.extension())) {
            return yamlMapper.readValue(fileHandle.read(), clazz);
        } else if ("json".equals(fileHandle.extension())) {
            return jsonMapper.readValue(fileHandle.read(), clazz);
        } else {
            throw new IllegalArgumentException("Can't read file with extension " + fileHandle.extension());
        }
    }

}
