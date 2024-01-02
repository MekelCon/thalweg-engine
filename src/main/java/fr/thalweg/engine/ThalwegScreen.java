package fr.thalweg.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.component.TextureComponent;
import fr.thalweg.engine.component.TransformComponent;
import fr.thalweg.engine.entity.ActorEntity;
import fr.thalweg.engine.gen.ThalwegActorSchema;
import fr.thalweg.engine.gen.ThalwegGameScreenSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Asset;
import fr.thalweg.engine.model.AssetType;

public class ThalwegScreen extends ScreenAdapter {

    private final static String LOG_TAG = "ThalwegScreen";
    public final String sourceFile;
    private final ThalwegGame thalwegGame;
    private final SpriteBatch batch;
    private final Viewport viewport;
    private final ThalwegGameScreenSchema screenData;

    public ThalwegScreen(ThalwegGame thalwegGame, String sourceFile, SpriteBatch batch, Viewport viewport) {
        Gdx.app.debug(LOG_TAG, "Creating new screen : " + sourceFile);
        this.thalwegGame = thalwegGame;
        this.sourceFile = sourceFile;
        this.batch = batch;
        this.viewport = viewport;
        screenData = Reader.getInstance().read(
                Asset.of(thalwegGame.getRoot(), AssetType.screen(), sourceFile).getFileHandle(),
                ThalwegGameScreenSchema.class);
        initActors();
    }

    private void initActors() {
        for (ThalwegActorSchema actorSchema : screenData.getActors()) {
            ActorEntity actorEntity = new ActorEntity();

            if (actorSchema.getTexture() != null) {
                Texture t = new Texture(
                        this.thalwegGame.getRoot().getSubFolder(actorSchema.getTexture()));
                t.setFilter(
                        Texture.TextureFilter.Nearest,
                        Texture.TextureFilter.Nearest);
                actorEntity.add(TextureComponent.builder()
                        .region(new TextureRegion(t))
                        .build());
            }

            if (actorSchema.getPosition() != null) {
                actorEntity.add(TransformComponent.builder()
                        .pos(new Vector3(
                                actorSchema.getPosition().getX(),
                                actorSchema.getPosition().getY(),
                                actorSchema.getPosition().getZ()))
                        .scale(new Vector2(1.0f, 1.0f))
                        .build());
            }
            this.thalwegGame.getECSEngine().addEntity(actorEntity);
        }
    }

    @Override
    public void render(float delta) {
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.thalwegGame.getECSEngine().update(delta);
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }
}
