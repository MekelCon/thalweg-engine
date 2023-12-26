package fr.thalweg.engine.utils;

import fr.thalweg.engine.ThalwegGame;

public class JsonYamlThalwegGame extends ThalwegGame {

    private final static String ROOT = "./src/test/resources/json-yaml";

    public JsonYamlThalwegGame() {
        super(ROOT);
        build(ROOT);
    }
}
