package fr.thalweg.engine.transformer.toECS;

import com.badlogic.gdx.math.Vector2;
import fr.thalweg.engine.infra.schema.Position;

public class ToVector2Position {
    public static Vector2 from(Position position) {
        if (position != null) {
            return new Vector2(position.getX(), position.getY());
        }
        return new Vector2(0, 0);
    }
}
