package fr.thalweg.engine.infra.data;

import com.badlogic.gdx.utils.Array;
import fr.thalweg.engine.infra.data.trigger.TriggerData;

public class ThalwegActorData {

    public PositionData position;
    public ScaleData scale;
    public String texture;
    public Array<XYData> vertices;
    public Array<TriggerData> triggers;
}

