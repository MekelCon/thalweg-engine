package fr.thalweg.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import fr.thalweg.engine.ThalwegEngineGame;
import fr.thalweg.engine.tolibgdx.ToLwjgl3ApplicationConfiguration;

public class Quercus {

    public static void main(String[] arg) {
        ThalwegEngineGame thalwegEngineGame = ThalwegEngineGame.build("./src/main/resources/quercus");

        new Lwjgl3Application(
                thalwegEngineGame,
                ToLwjgl3ApplicationConfiguration.from(thalwegEngineGame.getConfig().getLwjgl3ApplicationConfiguration()));
    }

}
