package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Builder;

@Builder
public class TextureComponent implements Component {
    public TextureRegion region;
}
