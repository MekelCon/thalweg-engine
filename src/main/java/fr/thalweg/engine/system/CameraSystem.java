package fr.thalweg.engine.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.ThalwegGame;
import lombok.Getter;

public class CameraSystem extends EntitySystem {
    final SpriteBatch batch;

    @Getter
    final Viewport viewport;

    public CameraSystem(SpriteBatch batch) {
        super(0);
        this.batch = batch;
        this.viewport = new FitViewport(
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth(),
                ThalwegGame.get().getConfig().getVirtualScreen().getHeight(),
                new OrthographicCamera(
                        ThalwegGame.get().getConfig().getVirtualScreen().getWidth(),
                        ThalwegGame.get().getConfig().getVirtualScreen().getHeight()));
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.app.debug("CameraSystem", "update");
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
    }
}
