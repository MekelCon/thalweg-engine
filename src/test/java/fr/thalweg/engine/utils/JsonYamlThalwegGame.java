package fr.thalweg.engine.utils;

import com.badlogic.gdx.ApplicationAdapter;
import fr.thalweg.engine.model.Directory;

public class JsonYamlThalwegGame extends ApplicationAdapter {

    private final static String ROOT = "./src/test/resources/json-yaml";
    public final static Directory ROOT_DIRECTORY = Directory.of(ROOT);

    public JsonYamlThalwegGame() {
        super();
    }
}
