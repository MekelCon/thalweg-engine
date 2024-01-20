package fr.thalweg.engine.infra.data.task;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class ParallelTaskData extends TaskArrayData {

    @Override
    public ParallelTaskData copy() {
        return ParallelTaskData.Cloner.INSTANCE.clone(this);
    }

    @Mapper(
            mappingControl = DeepClone.class,
            uses = ArrayCloner.class
    )
    public interface Cloner {
        ParallelTaskData.Cloner INSTANCE = Mappers.getMapper(ParallelTaskData.Cloner.class);

        ParallelTaskData clone(ParallelTaskData source);
    }

}

