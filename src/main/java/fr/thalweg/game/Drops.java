package fr.thalweg.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import fr.thalweg.engine.ThalwegEngineGame;
import fr.thalweg.engine.tolibgdx.ToLwjgl3ApplicationConfiguration;

public class Drops {

    public static void main(String[] arg) {
        ThalwegEngineGame thalwegEngineGame = new ThalwegEngineGame("drops");

        new Lwjgl3Application(
                thalwegEngineGame,
                ToLwjgl3ApplicationConfiguration.from(thalwegEngineGame.getGameConfigurationSchema().getLwjgl3ApplicationConfiguration()));
    }

}
