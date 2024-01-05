package fr.thalweg.engine;

import com.badlogic.gdx.Gdx;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Todo {

    String message;

    public boolean act(float deltaTime) {
        Gdx.app.log("TODO", message);
        return true;
    }
}
