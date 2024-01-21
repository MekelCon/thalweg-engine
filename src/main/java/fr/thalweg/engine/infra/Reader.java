package fr.thalweg.engine.infra;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import fr.thalweg.engine.infra.data.task.LogTaskData;
import fr.thalweg.engine.infra.data.task.TaskData;
import fr.thalweg.engine.infra.data.task.TaskTypeEnumData;
import fr.thalweg.engine.infra.data.trigger.TriggerTypeEnumData;
import lombok.Getter;
import lombok.SneakyThrows;

public class Reader {

    @Getter
    private static final Reader instance = new Reader();

    private static Engine ENGINE;

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

        }

        json.setSerializer(LogTaskData.class, new TaskSerializer(LogTaskData.class));
    }

    public static void setEcsEngine(PooledEngine ecsEngine) {
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

    public static class TaskSerializer<T extends TaskData> implements Json.Serializer<T> {

        final Class<T> baseType;

        public TaskSerializer(Class<T> baseType) {
            this.baseType = baseType;
        }

        @Override
        public void write(Json json, TaskData object, Class knownType) {
            // TODO ?
        }

        @Override
        public T read(Json json, JsonValue jsonData, Class type) {
            T result = (T) Reader.ENGINE.createComponent(type);
            json.readFields(result, jsonData);
            return result;
        }
    }
}
