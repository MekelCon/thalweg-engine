package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class TaskArrayData extends TaskData {
    public static final String JSON_PROPERTY_TODOS = "todos";
    public Array<TaskData> todos = new Array<>();

    public TaskArrayData() {
        super();
    }

    public TaskArrayData todos(Array<TaskData> todos) {

        this.todos = todos;
        return this;
    }

    public TaskArrayData addTodosItem(TaskData todosItem) {
        if (this.todos == null) {
            this.todos = new Array<>();
        }
        this.todos.add(todosItem);
        return this;
    }

    /**
     * Get todos
     *
     * @return todos
     **/
    @jakarta.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_TODOS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public Array<TaskData> getTodos() {
        return todos;
    }


    @JsonProperty(JSON_PROPERTY_TODOS)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setTodos(Array<TaskData> todos) {
        this.todos = todos;
    }

    public TaskArrayData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        TaskArrayData clone(TaskArrayData source);


        default Array<TaskData> mapTaskData(Array<TaskData> source) {
            if (source == null) {
                return null;
            }
            var res = new Array<>(source.size);
            for (TaskData t : source.toArray())
                res.add(TaskData.Cloner.INSTANCE.clone(t));
            return new Array<>(source);
        }
    }

}

