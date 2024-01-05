package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class SpriteComponent implements Component {
    @NonNull
    public Sprite sprite;
    public int zIndex;

}
