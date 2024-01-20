package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class ThalwegScreenData {

    public String name;
    public Array<ThalwegActorData> actors = new Array<>();

    public ThalwegScreenData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        ThalwegScreenData clone(ThalwegScreenData source);

        default Array<ThalwegActorData> mapThalwegActorData(Array<ThalwegActorData> source) {
            if (source == null) {
                return null;
            }
            var res = new Array<>(source.size);
            for (ThalwegActorData t : source.toArray())
                res.add(ThalwegActorData.Cloner.INSTANCE.clone(t));
            return new Array<>(source);
        }
    }
}

