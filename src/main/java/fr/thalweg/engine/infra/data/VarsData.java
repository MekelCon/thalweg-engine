package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class VarsData {
    public VarsExistingData existing;
    public Array<CustomVarData> custom;

    public VarsData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        VarsData clone(VarsData source);

        default VarsExistingData mapVarsExistingData(VarsExistingData source) {
            return VarsExistingData.Cloner.INSTANCE.clone(source);
        }

        default Array<CustomVarData> mapCustomVarData(Array<CustomVarData> source) {
            if (source == null) {
                return null;
            }
            var res = new Array<>(source.size);
            for (CustomVarData t : source.toArray())
                res.add(CustomVarData.Cloner.INSTANCE.clone(t));
            return new Array<>(source);
        }
    }
}

