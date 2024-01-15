package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.Builder;

@Builder
public class SpriteComponent implements Component {
    public Sprite sprite;
}
