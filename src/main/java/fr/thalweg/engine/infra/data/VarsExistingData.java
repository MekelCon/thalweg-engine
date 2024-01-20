package fr.thalweg.engine.infra.data;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class VarsExistingData {

    public String mouseLabelDefaultToken;

    public VarsExistingData() {
    }

    public VarsExistingData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        VarsExistingData clone(VarsExistingData source);
    }
}

