package fr.thalweg.engine.infra.data.task;

import fr.thalweg.engine.infra.data.InterpolationData;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class OverTimeTaskData extends TaskData {

    public float duration = 0f;
    public InterpolationData interpolation = InterpolationData.LINEAR;
    public float delay = 0f;

    public OverTimeTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        OverTimeTaskData clone(OverTimeTaskData source);
    }
}

