package fr.thalweg.engine.component.trigger.todo;

import com.badlogic.gdx.Gdx;
import lombok.Builder;

@Builder
public class GDXAppLog extends Todo {

    private String message;

    @Override
    public boolean act(float deltaTime) {
        Gdx.app.log("TODO", message);
        return true;
    }
}
