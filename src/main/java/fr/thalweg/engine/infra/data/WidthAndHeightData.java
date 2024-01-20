package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class WidthAndHeightData {
    public int width = 0;
    public int height = 0;

    public WidthAndHeightData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        WidthAndHeightData clone(WidthAndHeightData source);
    }
}

