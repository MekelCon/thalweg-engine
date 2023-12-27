package fr.thalweg.engine.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import lombok.Builder;

@Builder
public class TransformComponent implements Component {
    public final Vector3 pos = new Vector3(0, 0, 0);
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation;
}
