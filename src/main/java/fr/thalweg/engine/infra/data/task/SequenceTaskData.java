package fr.thalweg.engine.infra.data.task;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class SequenceTaskData extends TaskArrayData {

    @Override
    public SequenceTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(
            mappingControl = DeepClone.class,
            uses = ArrayCloner.class
    )
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        SequenceTaskData clone(SequenceTaskData source);
    }
}

