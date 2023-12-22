package fr.thalweg.engine.utils;

import fr.thalweg.engine.ThalwegEngineGame;

public class JsonYamlThalwegEngineGame extends ThalwegEngineGame {

    private final static String ROOT = "./src/test/resources/json-yaml";

    public JsonYamlThalwegEngineGame() {
        super(ROOT);
        build(ROOT);
    }
}
