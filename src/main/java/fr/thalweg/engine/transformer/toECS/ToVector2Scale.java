package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.math.Vector2;
import fr.thalweg.engine.infra.data.ScaleData;

public class ToVector2Scale {
    public static Vector2 from(ScaleData scale) {
        if (scale != null) {
            return new Vector2(scale.getX(), scale.getY());
        }
        return new Vector2(1, 1);
    }
}
