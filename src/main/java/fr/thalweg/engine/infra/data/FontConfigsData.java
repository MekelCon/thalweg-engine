package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class FontConfigsData {

    public Array<FontConfigData> family;
    public VarsData vars;

    public FontConfigsData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        FontConfigsData clone(FontConfigsData source);


        default Array<FontConfigData> mapFontConfigData(Array<FontConfigData> source) {
            if (source == null) {
                return null;
            }
            var res = new Array<>(source.size);
            for (FontConfigData t : source.toArray())
                res.add(FontConfigData.Cloner.INSTANCE.clone(t));
            return new Array<>(source);
        }

        default VarsData mapVarsData(VarsData source) {
            return VarsData.Cloner.INSTANCE.clone(source);
        }
    }
}

