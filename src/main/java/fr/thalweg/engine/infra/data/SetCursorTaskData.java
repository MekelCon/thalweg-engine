package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class SetCursorTaskData extends TaskData {

    public String cursor;
    public int xHotspot = 0;
    public int yHotspot = 0;

    public SetCursorTaskData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        SetCursorTaskData clone(SetCursorTaskData source);
    }
}

