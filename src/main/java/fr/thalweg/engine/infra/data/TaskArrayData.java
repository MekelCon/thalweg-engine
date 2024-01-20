package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class TaskArrayData extends TaskData {
    public Array<TaskData> todos = new Array<>();

    public TaskArrayData() {
        super();
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

