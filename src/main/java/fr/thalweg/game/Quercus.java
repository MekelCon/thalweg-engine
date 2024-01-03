package fr.thalweg.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import fr.thalweg.engine.ThalwegGame;
import fr.thalweg.engine.transformer.tolibgdx.ToLwjgl3ApplicationConfiguration;

public class Quercus {

    public static void main(String[] arg) {
        ThalwegGame thalwegGame = ThalwegGame.build("./src/main/resources/quercus");

        new Lwjgl3Application(
                thalwegGame,
                ToLwjgl3ApplicationConfiguration.from(thalwegGame.getConfig().getLwjgl3ApplicationConfiguration()));
    }

}
