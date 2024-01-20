package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class SequenceTaskData extends TaskArrayData {

    public SequenceTaskData() {
        super();
    }

    public SequenceTaskData copy() {
        return SequenceTaskData.Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        SequenceTaskData.Cloner INSTANCE = Mappers.getMapper(SequenceTaskData.Cloner.class);

        SequenceTaskData clone(SequenceTaskData source);

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

