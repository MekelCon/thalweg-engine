package fr.thalweg.engine;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.infra.data.ThalwegGameConfigurationData;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.system.CameraSystem;
import fr.thalweg.engine.system.rendering.DebugInfoRenderingSystem;
import fr.thalweg.engine.system.rendering.MouseTriggerDebugRenderingSystem;
import fr.thalweg.engine.system.rendering.TextRenderingSystem;
import fr.thalweg.engine.system.rendering.WorldRenderingSystem;
import fr.thalweg.engine.system.task.ParallelTask;
import fr.thalweg.engine.system.task.SequenceTask;
import fr.thalweg.engine.system.task.oneshot.LogTask;
import fr.thalweg.engine.system.task.oneshot.SetCursorTask;
import fr.thalweg.engine.system.task.overtime.PlayTransitionTask;
import fr.thalweg.engine.system.task.overtime.SetMouseLabelTask;
import fr.thalweg.engine.system.task.overtime.WaitTask;
import fr.thalweg.engine.system.trigger.AutoTriggerSystem;
import fr.thalweg.engine.system.trigger.MouseTriggerSystem;
import fr.thalweg.engine.transformer.tolibgdx.ToLogLevel;
import fr.thalweg.engine.validator.ProjectStructureValidator;
import lombok.Getter;
import lombok.extern.java.Log;

@Getter
@Log
public class ThalwegGame extends Game {

    public static ThalwegGame INSTANCE;
    private final Directory root;
    private final ThalwegGameConfigurationData config;
    private final ThalwegPooledEngine ecsEngine;
    private SpriteBatch batch;
    private Viewport viewport;
    private Viewport textViewport;

    protected ThalwegGame(
            String root
    ) {
        this.root = new Directory(root);
        this.config = Reader.getInstance().read(
                new PublicFileHandle(root + "/configuration.json", Files.FileType.Internal),
                ThalwegGameConfigurationData.class);
        this.ecsEngine = new ThalwegPooledEngine(10, 50, 20, 100);
        Reader.setEcsEngine(ecsEngine);
    }

    public static ThalwegGame build(String rootDirectory) {
        INSTANCE = new ThalwegGame(rootDirectory);
        return INSTANCE;
    }

    @Override
    public void create() {
        if (config.debug) {
            ProjectStructureValidator.validThalwegGameStructure(root);
        }
        initGdxConfig();
        batch = new SpriteBatch();
        // TODO : manage viewport type
        viewport = new FitViewport(
                config.world.width,
                config.world.height
        );
        textViewport = new ScreenViewport();

        var cameraSystem = new CameraSystem(config.world);
        ecsEngine.addSystem(cameraSystem);
        ecsEngine.addSystem(new WorldRenderingSystem(config.world, batch, viewport));
        if (config.debug) {
            ecsEngine.addSystem(new MouseTriggerDebugRenderingSystem(viewport));
            ecsEngine.addSystem(new DebugInfoRenderingSystem(textViewport));
        }
        ecsEngine.addSystem(new TextRenderingSystem(root, textViewport));


        ecsEngine.addSystem(new MouseTriggerSystem(viewport));
        ecsEngine.addSystem(new AutoTriggerSystem());
        // Task System
        // 1st treat wrapper task, so atomic tack will be executed during the same frame
        ecsEngine.addSystem(new ParallelTask());
        ecsEngine.addSystem(new SequenceTask());
        // Atomic Task
        ecsEngine.addSystem(new LogTask());
        ecsEngine.addSystem(new PlayTransitionTask());
        ecsEngine.addSystem(new SetCursorTask());
        ecsEngine.addSystem(new SetMouseLabelTask());
        ecsEngine.addSystem(new WaitTask());

        setScreen(new ThalwegScreen(
                this,
                config.startScreen,
                batch,
                cameraSystem.getCamera(),
                viewport,
                textViewport));
    }

    private void initGdxConfig() {
        Gdx.app.setLogLevel(ToLogLevel.from(config.gdx.logLevel));
    }

    // TODO : get rid of this
    private static class PublicFileHandle extends FileHandle {
        public PublicFileHandle(String path, Files.FileType type) {
            super(path, type);
        }
    }
}
