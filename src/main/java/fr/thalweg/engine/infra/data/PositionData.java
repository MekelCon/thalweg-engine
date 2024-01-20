package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class PositionData {

    public float x = 0f;
    public float y = 0f;
    public int z = 0;

    public PositionData() {
    }

    public PositionData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        PositionData clone(PositionData source);
    }
}

