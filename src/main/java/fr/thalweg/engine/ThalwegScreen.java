package fr.thalweg.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.thalweg.engine.gen.ThalwegGameScreenSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Asset;
import fr.thalweg.engine.model.AssetType;

public class ThalwegScreen implements Screen {

    private final static String LOG_TAG = "ThalwegScreen";
    public final String sourceFile;

    private final ThalwegGameScreenSchema screenData;

    // private final OrthographicCamera camera;
    private final Stage stage;

    public ThalwegScreen(String sourceFile) {
        Gdx.app.debug(LOG_TAG, "Creating new screen : " + sourceFile);
        // Data related
        this.sourceFile = sourceFile;
        screenData = Reader.getInstance().read(
                Asset.of(AssetType.screen(), sourceFile).getFileHandle(),
                ThalwegGameScreenSchema.class);
        // UI Related
        /*
        camera = new OrthographicCamera(
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth(),
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth());

         */
        stage = this.initStage();
    }

    private Stage initStage() {
        return new Stage(new FitViewport(
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth(),
                ThalwegGame.get().getConfig().getVirtualScreen().getWidth()));
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
