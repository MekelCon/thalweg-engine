package fr.thalweg.engine;

import com.badlogic.gdx.Game;
import fr.thalweg.engine.model.Directory;
import lombok.extern.java.Log;

@Log
public class ThalwegEngineGame extends Game {

    final Directory rootDirectory;

    public ThalwegEngineGame(
            String rootDirectory
    ) {
        this.rootDirectory = Directory.of(rootDirectory);
    }

    @Override
    public void create() {
        log.info("Should build game here");
    }
}
