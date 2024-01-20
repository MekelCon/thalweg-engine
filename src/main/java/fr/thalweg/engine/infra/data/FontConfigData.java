package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class FontConfigData {

    public String name;
    public String source;
    public int size = 12;
    public int borderSize = 1;
    public float xAdjust = 0f;
    public float yAdjust = 0f;
    public float widthAdjust = 0f;
    public float heightAdjust = 0f;

    public FontConfigData() {
    }

    public FontConfigData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        FontConfigData clone(FontConfigData source);
    }
}

