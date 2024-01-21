package fr.thalweg.engine.transformer.toECS;

import fr.thalweg.engine.infra.data.PositionData;

public class ToZIndex {
    public static int from(PositionData positionData) {
        if (positionData != null) {
            return positionData.z;
        }
        return 0;
    }
}
