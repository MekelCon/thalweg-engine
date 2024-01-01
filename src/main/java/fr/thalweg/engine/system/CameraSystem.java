package fr.thalweg.engine.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import fr.thalweg.engine.ThalwegGame;
import lombok.Getter;

@Getter
public class CameraSystem extends EntitySystem {

    private final OrthographicCamera camera;

    public CameraSystem() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(
                true,
                ThalwegGame.get().getConfig().getWorld().getWidth(),
                ThalwegGame.get().getConfig().getWorld().getHeight()
        );
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.camera.update();
    }
}
