package fr.thalweg.engine.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import fr.thalweg.engine.gen.World;
import lombok.Getter;

@Getter
public class CameraSystem extends EntitySystem {

    private final OrthographicCamera camera;

    public CameraSystem(World world) {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(
                true,
                world.getWidth(),
                world.getHeight()
        );
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.camera.update();
    }
}
