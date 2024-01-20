package fr.thalweg.engine.transformer.tolibgdx;

import com.badlogic.gdx.graphics.Color;
import fr.thalweg.engine.infra.data.ColorData;

public class ToColor {
    public static Color from(ColorData color) {
        return new Color(color.getR(), color.getG(), color.getB(), color.getA());
    }
}
