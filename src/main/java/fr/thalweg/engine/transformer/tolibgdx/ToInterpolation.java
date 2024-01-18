package fr.thalweg.engine.transformer.tolibgdx;

import com.badlogic.gdx.math.Interpolation;
import fr.thalweg.gen.engine.model.InterpolationData;

public class ToInterpolation {

    public static Interpolation from(InterpolationData interpolation) {
        return switch (interpolation) {
            case LINEAR -> Interpolation.linear;
            case POW -> Interpolation.pow2;
        };
    }
}
