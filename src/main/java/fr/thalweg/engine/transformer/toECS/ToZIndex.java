package fr.thalweg.engine.transformer.toECS;


import fr.thalweg.gen.engine.model.PositionData;

public class ToZIndex {
    public static int from(PositionData positionData) {
        if (positionData != null) {
            return positionData.getZ();
        }
        return 0;
    }
}
