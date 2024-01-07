package fr.thalweg.engine;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.thalweg.gen.engine.model.ThalwegGameConfigurationData;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.system.CameraSystem;
import fr.thalweg.engine.system.RenderingSystem;
import fr.thalweg.engine.system.task.TaskTodoSystem;
import fr.thalweg.engine.system.trigger.MouseTriggerDebugRenderingSystem;
import fr.thalweg.engine.system.trigger.MouseTriggerSystem;
import fr.thalweg.engine.transformer.tolibgdx.ToLogLevel;
import fr.thalweg.engine.validator.ProjectStructureValidator;
import lombok.Getter;
import lombok.extern.java.Log;

@Getter
@Log
public class ThalwegGame extends Game {

    private static ThalwegGame INSTANCE;
    private final Directory root;
    private final ThalwegGameConfigurationData config;
    private final PooledEngine ECSEngine;
    private SpriteBatch batch;
    private Viewport viewport;

    protected ThalwegGame(
            String root
    ) {
        this.root = Directory.of(root);
        this.config = Reader.getInstance().read(
                new PublicFileHandle(root + "/configuration.yaml", Files.FileType.Internal),
                ThalwegGameConfigurationData.class);
        this.ECSEngine = new PooledEngine();
    }

    public static ThalwegGame build(String rootDirectory) {
        INSTANCE = new ThalwegGame(rootDirectory);
        return INSTANCE;
    }

    @Override
    public void create() {
        if (config.isDebug()) {
            ProjectStructureValidator.validThalwegGameStructure(root);
        }
        initGdxConfig();
        batch = new SpriteBatch();
        // TODO : manage viewport type
        viewport = new FitViewport(
                config.getWorld().getWidth(),
                config.getWorld().getHeight()
        );
        ECSEngine.addSystem(new TaskTodoSystem());
        CameraSystem cameraSystem = new CameraSystem(config.getWorld());
        ECSEngine.addSystem(cameraSystem);
        ECSEngine.addSystem(new RenderingSystem(config.getWorld(), batch, viewport));
        if (config.isDebug()) {
            ECSEngine.addSystem(new MouseTriggerDebugRenderingSystem(viewport));
        }
        ECSEngine.addSystem(new MouseTriggerSystem(viewport));

        setScreen(new ThalwegScreen(this, config.getStartScreen(), batch, cameraSystem.getCamera(), viewport));
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
