package fr.thalweg.engine;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import fr.thalweg.engine.gen.GameConfigurationSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Directory;
import fr.thalweg.engine.tolibgdx.ToLogLevel;
import fr.thalweg.engine.validator.ProjectStructureValidator;
import lombok.Getter;
import lombok.extern.java.Log;

@Getter
@Log
public class ThalwegGame extends Game {

    private static ThalwegGame INSTANCE;
    final Directory root;
    final GameConfigurationSchema config;

    protected ThalwegGame(
            String root
    ) {
        this.root = Directory.of(root);
        this.config = Reader.getInstance().read(
                new PublicFileHandle(root + "/configuration.yaml", Files.FileType.Internal),
                GameConfigurationSchema.class);
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
            ProjectStructureValidator.validThalwegGameStructure();
        }
        initGdxConfig();
        this.setScreen(new ThalwegScreen(config.getStartScreen()));
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