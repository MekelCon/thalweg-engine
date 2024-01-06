package fr.thalweg.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.infra.schema.ThalwegActorSchema;
import fr.thalweg.engine.infra.schema.ThalwegGameScreenSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Asset;
import fr.thalweg.engine.model.AssetType;
import fr.thalweg.engine.transformer.toECS.ToEntity;

public class ThalwegScreen extends ScreenAdapter {

    private final static String LOG_TAG = "ThalwegScreen";
    public final String sourceFile;
    private final ThalwegGame thalwegGame;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final ThalwegGameScreenSchema screenData;

    public ThalwegScreen(ThalwegGame thalwegGame, String sourceFile, SpriteBatch batch, OrthographicCamera camera, Viewport viewport) {
        Gdx.app.debug(LOG_TAG, "Creating new screen : " + sourceFile);
        this.thalwegGame = thalwegGame;
        this.sourceFile = sourceFile;
        this.batch = batch;
        this.camera = camera;
        this.viewport = viewport;
        this.screenData = Reader.getInstance().read(
                Asset.of(thalwegGame.getRoot(), AssetType.screen(), sourceFile).getFileHandle(),
                ThalwegGameScreenSchema.class);
        initActors();
    }

    private void initActors() {
        for (ThalwegActorSchema actorSchema : screenData.getActors()) {
            thalwegGame.getECSEngine().addEntity(ToEntity.from(
                    thalwegGame.getRoot(),
                    actorSchema
            ));
        }
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        thalwegGame.getECSEngine().update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
