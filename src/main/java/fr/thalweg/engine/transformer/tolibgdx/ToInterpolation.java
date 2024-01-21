package fr.thalweg.engine.transformer.tolibgdx;

import com.badlogic.gdx.math.Interpolation;
import fr.thalweg.engine.infra.data.InterpolationData;

public class ToInterpolation {

    public static Interpolation from(InterpolationData interpolation) {
        if (interpolation == null) {
            return null;
        }
        return switch (interpolation) {
            case LINEAR -> Interpolation.linear;
            case POW -> Interpolation.pow2;
        };
    }
}
