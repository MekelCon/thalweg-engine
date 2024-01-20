package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class PlayTransitionTaskData extends OverTimeTaskData {

    public String transition;

    public PlayTransitionTaskData() {
        super();
    }

    public PlayTransitionTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        PlayTransitionTaskData clone(PlayTransitionTaskData source);
    }
}

