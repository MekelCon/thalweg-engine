package fr.thalweg.engine;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import fr.thalweg.engine.gen.GameConfigurationSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Directory;
import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class ThalwegEngineGame extends Game {

    final Directory rootDirectory;
    @Getter
    final GameConfigurationSchema gameConfigurationSchema;

    public ThalwegEngineGame(
            String rootDirectory
    ) {
        this.rootDirectory = Directory.of(rootDirectory);
        this.gameConfigurationSchema = Reader.getInstance().read(
                new PublicFileHandle(rootDirectory + "/configuration.yaml", Files.FileType.Classpath),
                GameConfigurationSchema.class);
    }

    @Override
    public void create() {

    }

    // TODO : get rid of this
    private static class PublicFileHandle extends FileHandle {
        public PublicFileHandle(String path, Files.FileType type) {
            super(path, type);
        }
    }
}
