package fr.thalweg.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.gen.Lwjgl3ApplicationConfigurationSchema;
import fr.thalweg.engine.infra.Reader;
import fr.thalweg.engine.model.Directory;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@Log
public class ThalwegEngineGame extends Game {

    final Directory rootDirectory;
    @Getter
    private Lwjgl3ApplicationConfigurationSchema lwjgl3ApplicationConfigurationSchema;

    public ThalwegEngineGame(
            String rootDirectory
    ) {
        this.rootDirectory = Directory.of(rootDirectory);
    }

    @SneakyThrows
    @Override
    public void create() {
        this.lwjgl3ApplicationConfigurationSchema = Reader.getInstance().read(
                Gdx.files.internal(rootDirectory + "/configuration.json"),
                Lwjgl3ApplicationConfigurationSchema.class);
    }
}
