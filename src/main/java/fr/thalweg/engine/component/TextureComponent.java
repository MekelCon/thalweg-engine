package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class TextureComponent implements Component {
    @NonNull
    public TextureRegion region;
}
