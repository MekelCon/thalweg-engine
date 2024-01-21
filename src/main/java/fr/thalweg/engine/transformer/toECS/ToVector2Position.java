package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.math.Vector2;
import fr.thalweg.engine.infra.data.PositionData;

public class ToVector2Position {
    public static Vector2 from(PositionData positionSchema) {
        if (positionSchema != null) {
            return new Vector2(positionSchema.x, positionSchema.y);
        }
        return new Vector2(0, 0);
    }
}
