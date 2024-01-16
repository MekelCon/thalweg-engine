package fr.thalweg.engine.utils;

import com.badlogic.gdx.ApplicationAdapter;
import fr.thalweg.engine.model.Directory;

public class BasicThalwegGame extends ApplicationAdapter {
    public final static String ROOT = "./src/test/resources/basic";
    public final static Directory ROOT_DIRECTORY = Directory.of(ROOT);

    public BasicThalwegGame() {
        super();
    }
}
