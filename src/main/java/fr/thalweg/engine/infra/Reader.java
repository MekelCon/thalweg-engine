package fr.thalweg.engine.infra;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import fr.thalweg.engine.ThalwegPooledEngine;
import fr.thalweg.engine.component.task.TaskComp;
import fr.thalweg.engine.component.task.TaskTypeEnumData;
import fr.thalweg.engine.infra.data.trigger.TriggerTypeEnumData;
import lombok.Getter;
import lombok.SneakyThrows;

public class Reader {

    @Getter
    private static final Reader instance = new Reader();

    private static ThalwegPooledEngine ENGINE;

    @Getter
    private final Json json;

    private Reader() {
        this.json = new Json();
        this.json.setTypeName("type");
        for (TriggerTypeEnumData triggerType : TriggerTypeEnumData.values()) {
            this.json.addClassTag(triggerType.getValue(), triggerType.getTarget());
        }
        for (TaskTypeEnumData taskType : TaskTypeEnumData.values()) {
            this.json.addClassTag(taskType.value, taskType.target);
            json.setSerializer(taskType.target, new TaskSerializer<>());
        }
    }

    public static void setEcsEngine(ThalwegPooledEngine ecsEngine) {
        ENGINE = ecsEngine;
    }

    @SneakyThrows
    public <T> T read(FileHandle fileHandle, Class<T> clazz) {
        if ("json".equals(fileHandle.extension())) {
            return json.fromJson(clazz, fileHandle.read());
        } else {
            throw new IllegalArgumentException("Can't read file with extension " + fileHandle.extension());
        }
    }

    public static class TaskSerializer<T extends TaskComp> implements Json.Serializer<T> {

        @Override
        public void write(Json json, TaskComp object, Class knownType) {
            // TODO ?
        }

        @Override
        @SuppressWarnings("unchecked")
        public T read(Json json, JsonValue jsonData, Class type) {
            var result = (T) Reader.ENGINE.createComponent(type);
            json.readFields(result, jsonData);
            return result;
        }
    }
}
