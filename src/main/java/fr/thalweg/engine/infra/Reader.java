package fr.thalweg.engine.infra;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import fr.thalweg.engine.infra.data.task.TaskTypeEnumData;
import fr.thalweg.engine.infra.data.trigger.TriggerTypeEnumData;
import lombok.Getter;
import lombok.SneakyThrows;

public class Reader {

    @Getter
    private static final Reader instance = new Reader();

    @Getter
    private final Json json;

    private Reader() {
        this.json = new Json();
        for (TriggerTypeEnumData triggerType : TriggerTypeEnumData.values()) {
            this.json.addClassTag(triggerType.getValue(), triggerType.getTarget());
        }
        for (TaskTypeEnumData taskType : TaskTypeEnumData.values()) {
            this.json.addClassTag(taskType.getValue(), taskType.getTarget());
        }
    }

    @SneakyThrows
    public <T> T read(FileHandle fileHandle, Class<T> clazz) {
        if ("json".equals(fileHandle.extension())) {
            //Gdx.app.debug(Reader.class.getSimpleName(), "Reading file " + fileHandle.path());
            return json.fromJson(clazz, fileHandle.read());
        } else {
            throw new IllegalArgumentException("Can't read file with extension " + fileHandle.extension());
        }
    }

}
