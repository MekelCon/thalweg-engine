package fr.thalweg.engine.utils;

import fr.thalweg.engine.ThalwegEngineGame;

public class BasicThalwegEngineGame extends ThalwegEngineGame {

    private final static String ROOT = "./src/test/resources/basic";

    public BasicThalwegEngineGame() {
        super(ROOT);
        build(ROOT);
    }
}
