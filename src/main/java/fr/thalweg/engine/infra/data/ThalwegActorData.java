package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.infra.data.trigger.TriggerData;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

public class ThalwegActorData {

    public PositionData position;
    public ScaleData scale;
    public String texture;
    public Array<XYData> vertices;
    public Array<TriggerData> triggers;

    public ThalwegActorData copy() {
        return Cloner.INSTANCE.clone(this);
    }

    @Mapper(mappingControl = DeepClone.class)
    public interface Cloner {
        Cloner INSTANCE = Mappers.getMapper(Cloner.class);

        ThalwegActorData clone(ThalwegActorData source);

        default PositionData mapPositionData(PositionData source) {
            return PositionData.Cloner.INSTANCE.clone(source);
        }

        default ScaleData mapScaleData(ScaleData source) {
            return ScaleData.Cloner.INSTANCE.clone(source);
        }

        default Array<XYData> mapXYData(Array<XYData> source) {
            if (source == null) {
                return null;
            }
            var res = new Array<>(source.size);
            for (XYData t : source.toArray())
                res.add(XYData.Cloner.INSTANCE.clone(t));
            return new Array<>(source);
        }

        default Array<TriggerData> mapTriggerData(Array<TriggerData> source) {
            if (source == null) {
                return null;
            }
            var res = new Array<>(source.size);
            for (TriggerData t : source.toArray())
                res.add(TriggerData.Cloner.INSTANCE.clone(t));
            return new Array<>(source);
        }
    }
}

