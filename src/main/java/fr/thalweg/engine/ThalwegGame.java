package fr.thalweg.engine;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.thalweg.engine.gen.GameConfigurationSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.system.CameraSystem;
import fr.thalweg.engine.system.RenderingSystem;
import fr.thalweg.engine.tolibgdx.ToLogLevel;
import fr.thalweg.engine.validator.ProjectStructureValidator;
import lombok.Getter;
import lombok.extern.java.Log;

@Getter
@Log
public class ThalwegGame extends Game {

    private static ThalwegGame INSTANCE;
    private final Directory root;
    private final GameConfigurationSchema config;
    private final PooledEngine ECSEngine;
    private SpriteBatch batch;
    private FitViewport viewport;

    protected ThalwegGame(
            String root
    ) {
        this.root = Directory.of(root);
        this.config = Reader.getInstance().read(
                new PublicFileHandle(root + "/configuration.yaml", Files.FileType.Internal),
                GameConfigurationSchema.class);
        this.ECSEngine = new PooledEngine();
    }

    public static ThalwegGame build(String rootDirectory) {
        INSTANCE = new ThalwegGame(rootDirectory);
        return INSTANCE;
    }

    public static ThalwegGame get() {
        return INSTANCE;
    }

    @Override
    public void create() {
        if (config.isDebug()) {
            ProjectStructureValidator.validThalwegGameStructure(this.root);
        }
        initGdxConfig();
        this.batch = new SpriteBatch();
        this.viewport = new FitViewport(
                this.config.getWorld().getWidth(),
                this.config.getWorld().getHeight()
        );
        CameraSystem cameraSystem = new CameraSystem(this.config.getWorld());
        ECSEngine.addSystem(cameraSystem);
        ECSEngine.addSystem(new RenderingSystem(this.config.getWorld(), batch, cameraSystem.getCamera(), viewport));
        this.setScreen(new ThalwegScreen(this.root, config.getStartScreen(), batch, viewport));
    }

    private void initGdxConfig() {
        Gdx.app.setLogLevel(ToLogLevel.from(config.getGdx().getLogLevel()));
    }

    // TODO : get rid of this
    private static class PublicFileHandle extends FileHandle {
        public PublicFileHandle(String path, Files.FileType type) {
            super(path, type);
        }
    }


}
