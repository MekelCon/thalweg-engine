package fr.thalweg.engine.system.task;

import com.badlogic.gdx.Gdx;
import fr.thalweg.engine.infra.schema.task.ChangeCursorTaskSchema;
import lombok.Builder;

@Builder
public class ChangeCursorTask implements Task {
    private ChangeCursorTaskSchema data;

    @Override
    public boolean work(float deltaTime) {
        Gdx.app.log("ChangeCursorTask", data.getCursor());
        return true;
    }
}

