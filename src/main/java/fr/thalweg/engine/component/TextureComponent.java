package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import lombok.Builder;

@Builder
public class TextureComponent implements Component {
    public Texture region;
}
