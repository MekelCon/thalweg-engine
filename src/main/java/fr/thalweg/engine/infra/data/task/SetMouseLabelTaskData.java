package fr.thalweg.engine.infra.data.task;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class SetMouseLabelTaskData extends OverTimeTaskData {

    public String label;
    public String fontName;

    public SetMouseLabelTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        SetMouseLabelTaskData clone(SetMouseLabelTaskData source);
    }
}

