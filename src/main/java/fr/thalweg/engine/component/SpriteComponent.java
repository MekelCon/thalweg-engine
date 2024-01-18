package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;

public class SpriteComponent implements Component, Pool.Poolable {
    public Sprite sprite;

    @Override
    public void reset() {
        sprite = null;
    }
}
