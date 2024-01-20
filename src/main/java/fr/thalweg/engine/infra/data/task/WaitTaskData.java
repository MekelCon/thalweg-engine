package fr.thalweg.engine.infra.data.task;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class WaitTaskData extends OverTimeTaskData {

    @Override
    public WaitTaskData copy() {
        return WaitTaskData.Cloner.INSTANCE.clone(this);
    }

    @Mapper(
            mappingControl = DeepClone.class,
            uses = ArrayCloner.class
    )
    public interface Cloner {
        WaitTaskData.Cloner INSTANCE = Mappers.getMapper(WaitTaskData.Cloner.class);

        WaitTaskData clone(WaitTaskData source);
    }
}
