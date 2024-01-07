package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.math.Vector2;
import fr.thalweg.gen.engine.model.PositionData;

public class ToVector2Position {
    public static Vector2 from(PositionData positionSchema) {
        if (positionSchema != null) {
            return new Vector2(positionSchema.getX(), positionSchema.getY());
        }
        return new Vector2(0, 0);
    }
}
