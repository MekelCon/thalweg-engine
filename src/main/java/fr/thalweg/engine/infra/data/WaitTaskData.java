package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class WaitTaskData extends OverTimeTaskData {

    public WaitTaskData copy() {
        return WaitTaskData.Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        WaitTaskData.Cloner INSTANCE = Mappers.getMapper(WaitTaskData.Cloner.class);

        WaitTaskData clone(WaitTaskData source);
    }
}