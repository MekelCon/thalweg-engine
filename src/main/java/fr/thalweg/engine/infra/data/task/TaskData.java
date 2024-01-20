package fr.thalweg.engine.infra.data.task;

import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;


@Getter
public class TaskData {

    private final TaskTypeEnumData type;

    public TaskData() {
        type = Arrays.stream(TaskTypeEnumData.values())
                .filter(taskType -> taskType.getTarget().equals(this.getClass()))
                .findFirst()
                .orElseThrow();
    }

    public TaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        TaskData clone(TaskData source);
    }
}

