package fr.thalweg.engine.infra.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;

public class TriggerData {

    public final TriggerTypeEnumData type;
    public TaskData todo;

    public TriggerData() {
        type = Arrays.stream(TriggerTypeEnumData.values())
                .filter(triggerType -> triggerType.getTarget().equals(this.getClass()))
                .findFirst()
                .orElseThrow();
    }

    public TriggerData todo(TaskData todo) {

        this.todo = todo;
        return this;
    }

    /**
     * Get todo
     *
     * @return todo
     **/
    @jakarta.annotation.Nullable
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public TaskData getTodo() {
        return todo;
    }


    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTodo(TaskData todo) {
        this.todo = todo;
    }

    public TriggerData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        TriggerData clone(TriggerData source);

        default TaskData mapTaskData(TaskData source) {
            return TaskData.Cloner.INSTANCE.clone(source);
        }
    }

}

