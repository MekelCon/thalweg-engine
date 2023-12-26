package fr.thalweg.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import fr.thalweg.engine.gen.ThalwegGameScreenSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Asset;
import fr.thalweg.engine.model.AssetType;

public class ThalwegScreen implements Screen {

    private final static String LOG_TAG = "ThalwegScreen";
    public final String sourceFile;

    private final ThalwegGameScreenSchema sceneData;

    private final OrthographicCamera camera;

    public ThalwegScreen(String sourceFile) {
        Gdx.app.debug(LOG_TAG, "Creating new scene : " + sourceFile);
        this.sourceFile = sourceFile;
        camera = new OrthographicCamera(
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth(),
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth());
        sceneData = Reader.getInstance().read(
                Asset.of(AssetType.scene(), sourceFile).getFileHandle(),
                ThalwegGameScreenSchema.class);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
