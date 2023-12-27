package fr.thalweg.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.thalweg.engine.component.PositionComponent;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.gen.ThalwegActorSchema;
import fr.thalweg.engine.gen.ThalwegGameScreenSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Asset;
import fr.thalweg.engine.model.AssetType;
import fr.thalweg.engine.system.CameraSystem;

public class ThalwegScreen implements Screen {

    private final static String LOG_TAG = "ThalwegScreen";
    public final String sourceFile;

    private final ThalwegGameScreenSchema screenData;
    private final CameraSystem cameraSystem;

    public ThalwegScreen(String sourceFile, CameraSystem cameraSystem) {
        Gdx.app.debug(LOG_TAG, "Creating new screen : " + sourceFile);
        // Data related
        this.sourceFile = sourceFile;
        this.cameraSystem = cameraSystem;
        screenData = Reader.getInstance().read(
                Asset.of(AssetType.screen(), sourceFile).getFileHandle(),
                ThalwegGameScreenSchema.class);
        initActors();
    }

    private void initActors() {
        for (ThalwegActorSchema actorSchema : screenData.getActors()) {
            ActorEntity actorEntity = new ActorEntity();

            if (actorSchema.getTexture() != null) {
                actorEntity.add(TextureComponent.builder()
                        .region(new TextureRegion(new Texture(ThalwegGame.get().getRoot().getSubFolder(actorSchema.getTexture()))))
                        .build());
                actorEntity.add(TransformComponent.builder()
                        .build());
            }

            if (actorSchema.getPosition() != null) {
                actorEntity.add(PositionComponent.builder()
                        .x(actorSchema.getPosition().getX())
                        .y(actorSchema.getPosition().getY())
                        .build());
            }
            ThalwegGame.get().getECSEngine().addEntity(actorEntity);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ThalwegGame.get().getECSEngine().update(delta);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("ThalwegScreen", "resize");
        cameraSystem.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
