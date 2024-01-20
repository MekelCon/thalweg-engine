package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class ParallelTaskData extends TaskArrayData {

    public ParallelTaskData() {
        super();
    }

    public ParallelTaskData copy() {
        return ParallelTaskData.Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        ParallelTaskData.Cloner INSTANCE = Mappers.getMapper(ParallelTaskData.Cloner.class);

        ParallelTaskData clone(ParallelTaskData source);

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

