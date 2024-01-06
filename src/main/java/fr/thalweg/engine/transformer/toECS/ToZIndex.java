package fr.thalweg.engine.transformer.toECS;

import fr.thalweg.engine.infra.schema.Position;

public class ToZIndex {
    public static int from(Position position) {
        if (position != null) {
            return position.getZ();
        }
        return 0;
    }
}
