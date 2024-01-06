package fr.thalweg.engine.component.trigger.todo;

import com.badlogic.gdx.Gdx;
import lombok.Builder;

@Builder
public class GDXAppLog extends Todo {

    private String message;

    @Override
    public boolean doing(float deltaTime) {
        Gdx.app.log("TODO", message);
        return true;
    }
}
