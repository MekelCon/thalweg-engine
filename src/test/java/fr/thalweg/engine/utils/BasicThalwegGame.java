package fr.thalweg.engine.utils;

import fr.thalweg.engine.ThalwegGame;

public class BasicThalwegGame extends ThalwegGame {

    private final static String ROOT = "./src/test/resources/basic";

    public BasicThalwegGame() {
        super(ROOT);
        build(ROOT);
    }
}
